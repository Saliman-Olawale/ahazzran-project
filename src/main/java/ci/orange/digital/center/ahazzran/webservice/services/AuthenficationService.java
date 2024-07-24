package ci.orange.digital.center.ahazzran.webservice.services;

import ci.orange.digital.center.ahazzran.webservice.dtos.CheckEmailInputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.CheckEmailOutputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.ConnexionBackOfficeInputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.ConnexionInputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.ConnexionOutputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.InscriptionInputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.InscriptionOutputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.ReinitializePasswordMobileInputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.ReinitializePasswordMobileOutputDto;
import ci.orange.digital.center.ahazzran.webservice.entities.Apprenant;
import ci.orange.digital.center.ahazzran.webservice.entities.Langue;
import ci.orange.digital.center.ahazzran.webservice.entities.Niveau;
import ci.orange.digital.center.ahazzran.webservice.entities.Progression;
import ci.orange.digital.center.ahazzran.webservice.entities.Utilisateur;
import ci.orange.digital.center.ahazzran.webservice.repositories.ApprenantRepository;
import ci.orange.digital.center.ahazzran.webservice.repositories.LangueRepository;
import ci.orange.digital.center.ahazzran.webservice.repositories.NiveauRepository;
import ci.orange.digital.center.ahazzran.webservice.repositories.ProgressionRepository;
import ci.orange.digital.center.ahazzran.webservice.repositories.UtilisateurRepository;
import ci.orange.digital.center.ahazzran.webservice.utils.JwtUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenficationService implements IAuthenticationService {

    final private String cacheName = "AhazzranCache";
    final private String otpKey = "otpKey:";

    private final UtilisateurRepository utilisateurRepository;
    private final ApprenantRepository apprenantRepository;
    private final LangueRepository langueRepository;
    private final NiveauRepository niveauRepository;
    private final ProgressionRepository progressionRepository;
    private final CacheManager cacheManager;
    private final IMailService mailService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final UtilisateurDetailsService utilisateurDetailsService;
    private final ApprenantDetailsService apprenantDetailsService;

    public AuthenficationService(final UtilisateurRepository utilisateurRepository,
            final ApprenantRepository apprenantRepository, final LangueRepository langueRepository,
            final NiveauRepository niveauRepository, final ProgressionRepository progressionRepository,
            final CacheManager cacheManager,final UtilisateurDetailsService utilisateurDetailsService,
            final IMailService mailService, final PasswordEncoder passwordEncoder,
            final AuthenticationManager authenticationManager,final ApprenantDetailsService apprenantDetailsService,
            final JwtUtil jwtUtil) {
        this.utilisateurRepository = utilisateurRepository;
        this.apprenantRepository = apprenantRepository;
        this.cacheManager = cacheManager;
        this.mailService = mailService;
        this.passwordEncoder = passwordEncoder;
        this.langueRepository = langueRepository;
        this.niveauRepository = niveauRepository;
        this.progressionRepository = progressionRepository;
        this.jwtUtil = jwtUtil;
        this.utilisateurDetailsService = utilisateurDetailsService;
        this.apprenantDetailsService = apprenantDetailsService;
    }

    @Override
    public ConnexionOutputDto connexionMobile(ConnexionInputDto input) {

        UserDetails userDetails = apprenantDetailsService.loadUserByUsername(input.getEmail());

        Apprenant apprenant = apprenantRepository.findByEmail(input.getEmail());
        if (apprenant == null) {
            return new ConnexionOutputDto("Echec", "Aucun utilisateur avec cet email existe.");
        }
        String password = input.getPassword();
        if (password == null || password.isEmpty()) {
            return new ConnexionOutputDto("Echec", "Le mot de passe ne peut pas être nul ou vide");
        }
        if (!passwordEncoder.matches(password, apprenant.getMotDePasse())) {

            return new ConnexionOutputDto("Echec", "Email ou mot de passe incorrect");
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", userDetails.getAuthorities().stream()
                .filter(authority -> authority.getAuthority().startsWith("ROLE_"))
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));

        String token = jwtUtil.generateToken(apprenant.getEmail(), claims);

        return new ConnexionOutputDto("Succes", "Connexion réussie", token);
    }

   
    @Override
    public ConnexionOutputDto checkEmailBackOffice(ConnexionBackOfficeInputDto input) {

        Utilisateur utilisateur = utilisateurRepository.findByEmail(input.getEmail());

        if (utilisateur == null) {
            return new ConnexionOutputDto("Echec", "Aucun utilisateur avec cet email existe.");
        }

        String otp = getOneTimePassword();
        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null) { // Incomprehension a ce niveau cache
            cache.put(otpKey + input.getEmail(), otp);
        } else {
            return new ConnexionOutputDto("Echec", "La valeur du cache est nulle");
        }

        mailService.SendMailForConnexion(input.getEmail(), otp);
        return new ConnexionOutputDto("Succes","Vous aviez reçu un mail. Veuillez consulter votre boite de messagerie");

    }

    @Override
    public ConnexionOutputDto connexionBackOffice(ConnexionBackOfficeInputDto input) {

        UserDetails userDetails = utilisateurDetailsService.loadUserByUsername(input.getEmail());

        Utilisateur utilisateur = utilisateurRepository.findByEmail(input.getEmail());
        if (utilisateur == null) {
            return new ConnexionOutputDto("Echec", "Aucun utilisateur avec cet email existe.");
        }
        Cache cache = cacheManager.getCache(cacheName);
        if (cache == null) {
            return new ConnexionOutputDto("Echec", "La valeur du cache est nulle ");
        }

        String otp = cache.get(otpKey + input.getEmail(), String.class);

        if (otp == null || (otp != null && !otp.equals(input.getOtp()))) {
            return new ConnexionOutputDto("Echec", "La valeur de l'OTP saisi est nulle ou incorrecte ");
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", userDetails.getAuthorities().stream()
                .filter(authority -> authority.getAuthority().startsWith("ROLE_"))
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));

        String token = jwtUtil.generateToken(utilisateur.getEmail(),claims);

        return new ConnexionOutputDto("Succes", "Connexion réussie", token);

    }

    @Override
    public CheckEmailOutputDto checkEmail(CheckEmailInputDto input) {

        try {
            Apprenant apprenant = apprenantRepository.findByEmail(input.getEmail());
            if (input.isInscription()) {
                if (apprenant != null) { 
                    return new CheckEmailOutputDto("Echec", "Un apprenant avec cet email existe déjà");
                }
            }
            
            String otp = getOneTimePassword();
            Cache cache = cacheManager.getCache(cacheName);
            System.out.println(otp);
            if (cache != null) { 
                cache.put(otpKey + input.getEmail(), otp);
            } else {
                return new CheckEmailOutputDto("Echec", "La valeur du cache est nulle");
            }
            if (input.isInscription() && !input.isResetPincode()) {

                mailService.SendMailForInscription(input.getEmail(), otp);
            } else if (input.isResetPincode() && !input.isInscription()) {

                mailService.SendMailForResetPincode(input.getEmail(), otp);
            } else {
                return new CheckEmailOutputDto("Echec", "Verifer correctement les valeurs de" + input.isResetPincode()
                        + " et " + input.isInscription());
            }
            return new CheckEmailOutputDto("Succes","Vous aviez reçu un mail. Veuillez consulter votre boite de messagerie");
        } catch (Exception e) {
            return new CheckEmailOutputDto("Echec", "Verifier a nouveau vos infos. Voici l'erreur :" + e.getMessage());
        }
    }

    public InscriptionOutputDto inscriptionMobile(InscriptionInputDto input) {

        Cache cache = cacheManager.getCache(cacheName);
        if (cache == null) {
            return new InscriptionOutputDto("Echec", "La valeur du cache est nulle ");
        }

        String otp = cache.get(otpKey + input.getEmail(), String.class);

        
        if (otp == null || (otp != null && !otp.equals(input.getOtp()))) {
            return new InscriptionOutputDto("Echec", "La valeur de l'OTP saisi est nulle ou incorrecte ");
        }

        if (!input.getPassword().equals(input.getConfirmPassword())) {
            return new InscriptionOutputDto("Echec", "Reverifier le mot de passe de confirmation");
        }

        Apprenant apprenant = apprenantRepository.findByEmail(input.getEmail());
        if (apprenant != null) {
            return new InscriptionOutputDto("Echec", "Un apprenant avec cet email existe déjà ");
        }

        Langue langue = langueRepository.findById(input.getLangueId()).orElseThrow(() -> new IllegalStateException("Pas de langue existante avec cet id " + input.getLangueId()));
        Niveau niveau = niveauRepository.findById(input.getNiveauId()).orElseThrow(() -> new IllegalStateException("Pas de Niveau existant avec cet id " + input.getNiveauId()));

        Apprenant nouvelApprenant = new Apprenant();

        nouvelApprenant.setEmail(input.getEmail());
        String hashPassword = passwordEncoder.encode(input.getPassword());

        nouvelApprenant.setMotDePasse(hashPassword);
        nouvelApprenant.setLangue(langue);
        nouvelApprenant.setNiveau(niveau);
        Apprenant apprenantEnregistre = apprenantRepository.save(nouvelApprenant);


        Progression progression = new Progression();
        progression.setApprenant(apprenantEnregistre);
        progression.setLangue(langue);
        progression.setNiveau(niveau);
        progressionRepository.save(progression);
        cache.evictIfPresent(otpKey + input.getEmail());
        return new InscriptionOutputDto("Succes", "Votre inscription est réussie.");
    }

    @Override
    public ReinitializePasswordMobileOutputDto motDePasseOublieMobile(ReinitializePasswordMobileInputDto input) {

        Cache cache = cacheManager.getCache(cacheName);
        if (cache == null) {
            return new ReinitializePasswordMobileOutputDto("Echec", "La valeur du cache est nulle ");
        }

        String otp = cache.get(otpKey + input.getEmail(), String.class);

        if (otp == null || (otp != null && !otp.equals(input.getOtp()))) {
            return new ReinitializePasswordMobileOutputDto("Echec",
                    "La valeur de l'OTP saisi est nulle ou incorrecte ");
        }

        if (!input.getPassword().equals(input.getConfirmPassword())) {
            return new ReinitializePasswordMobileOutputDto("Echec", "Reverifier le mot de passe de confirmation");
        }

        Apprenant apprenant = apprenantRepository.findByEmail(input.getEmail());
        if (apprenant == null) {
            return new ReinitializePasswordMobileOutputDto("Echec", "Aucun apprenant avec cet email existe. ");
        }

        apprenant.setEmail(input.getEmail());
        String hashPassword = passwordEncoder.encode(input.getPassword());

        apprenant.setMotDePasse(hashPassword);
        apprenantRepository.save(apprenant);

        cache.evictIfPresent(otpKey + input.getEmail());
        return new ReinitializePasswordMobileOutputDto("Succès", "Votre mot de passe a été réinitialisé.");

    }

    private String getOneTimePassword() {
        return String.format("%04d", new Random().nextInt(10000));
    }

}
