package ci.orange.digital.center.ahazzran.webservice.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ci.orange.digital.center.ahazzran.webservice.dtos.ChangerMotDePasseInputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.ConnexionOutputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.EditerProfilInputDto;
import ci.orange.digital.center.ahazzran.webservice.entities.Apprenant;
import ci.orange.digital.center.ahazzran.webservice.repositories.ApprenantRepository;


@Service
public class ApprenantService implements IApprenantService{

    private final ApprenantRepository apprenantRepository;
    private final PasswordEncoder passwordEncoder;


    public ApprenantService(final ApprenantRepository apprenantRepository, final PasswordEncoder passwordEncoder) {
        this.apprenantRepository = apprenantRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public ConnexionOutputDto editProfil(EditerProfilInputDto input) {

        Apprenant apprenant = apprenantRepository.findById(input.getId()).orElseThrow(() -> new RuntimeException("Apprenant Pas trouvé"));

        apprenant.setEmail(input.getEmail());
        apprenant.setNom(input.getNom());
        apprenant.setPrenom(input.getPrenoms());
        apprenant.setTelephone(input.getTelephone());

        apprenantRepository.save(apprenant);

       return new ConnexionOutputDto("Succes", "Modification Réussie");
    }


    @Override
    public ConnexionOutputDto changeMotDePasse(ChangerMotDePasseInputDto input) {
      
        Apprenant apprenant = apprenantRepository.findById(input.getId()).orElseThrow(() -> new RuntimeException("Apprenant Pas trouvé"));

        String hashPassword = passwordEncoder.encode(input.getMotDePasse());

        apprenant.setMotDePasse(hashPassword);

        return new ConnexionOutputDto("Succes", "Mot de passe modifié");
    }

}
