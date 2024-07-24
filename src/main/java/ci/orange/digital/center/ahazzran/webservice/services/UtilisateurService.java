package ci.orange.digital.center.ahazzran.webservice.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ci.orange.digital.center.ahazzran.webservice.dtos.UtilisateurOutputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.CreateOrUpdateUtilisateurInputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.CreateOrUpdateUtilisateurOutputDto;
import ci.orange.digital.center.ahazzran.webservice.entities.Role;
import ci.orange.digital.center.ahazzran.webservice.entities.Utilisateur;
import ci.orange.digital.center.ahazzran.webservice.repositories.RoleRepository;
import ci.orange.digital.center.ahazzran.webservice.repositories.UtilisateurRepository;
import ci.orange.digital.center.ahazzran.webservice.services.UtilisateurService;


@Service
public class UtilisateurService implements IUtilisateurService {

    private UtilisateurRepository utilisateurRepository;

    private RoleRepository roleRepository;

    public UtilisateurService(final UtilisateurRepository utilisateurRepository, final RoleRepository roleRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<UtilisateurOutputDto> getAllUtilisateurs() {

        List<Utilisateur> utilisateurs = utilisateurRepository.findAll();

        return utilisateurs.stream().map(this::convertToDTO).collect(Collectors.toList());
    }


    @Override
    public CreateOrUpdateUtilisateurOutputDto createOrUpdateUtilisateur(CreateOrUpdateUtilisateurInputDto input) {
    CreateOrUpdateUtilisateurOutputDto result;
    try {
        Utilisateur utilisateur;
        if (input.getId() > 0) {
            // Mise à jour de l'utilisateur existant
            utilisateur = utilisateurRepository.findById(input.getId()).orElseThrow(
                    () -> new RuntimeException("Utilisateur non trouvé avec l'ID " + input.getId()));     
        } else {
            // Création d'un nouvel utilisateur
            utilisateur = new Utilisateur();
            utilisateur.setStatut("ENABLE");
        }
        utilisateur.setEmail(input.getEmail());
        Role role = roleRepository.findById(input.getRoleId()).orElseThrow(
            () -> new RuntimeException("Aucun role associe a cet " + input.getRoleId()));
        utilisateur.setRole(role);
        utilisateurRepository.save(utilisateur);
        result = new CreateOrUpdateUtilisateurOutputDto("Succès", "Un utilisateur existant a été modifié.");
    } catch (Exception e) {
        // Log the exception (optional)
        result = new CreateOrUpdateUtilisateurOutputDto("Echec", "Aucun ajout ou modification apporté sur un utilisateur. Erreur :" + e.getLocalizedMessage());
    }

    return result;
}
    @Override
    public boolean DisableUtilisateur(int utilisateurId) {
       try {
        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId).orElseThrow();
        utilisateur.setStatut("DISABLE");
        utilisateurRepository.save(utilisateur);
        } catch (Exception e){
         return false;
        }

        return true;
      
    }

    @Override
    public boolean EnableUtilisateur(int utilisateurId) {
         try {
            Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId).orElseThrow();
            utilisateur.setStatut("ENABLE");
            utilisateurRepository.save(utilisateur);
           } catch (Exception e) { return false;
           }
   
           return true;
    }

    private UtilisateurOutputDto convertToDTO(Utilisateur utilisateur) {
      return new UtilisateurOutputDto(utilisateur);
  }


}


