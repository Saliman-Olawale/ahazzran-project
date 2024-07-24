package ci.orange.digital.center.ahazzran.webservice.services;

import java.util.List;

import ci.orange.digital.center.ahazzran.webservice.dtos.CreateOrUpdateUtilisateurInputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.CreateOrUpdateUtilisateurOutputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.UtilisateurOutputDto;

public interface IUtilisateurService {

        List<UtilisateurOutputDto> getAllUtilisateurs();

        CreateOrUpdateUtilisateurOutputDto createOrUpdateUtilisateur(CreateOrUpdateUtilisateurInputDto input);

        boolean DisableUtilisateur(int utilisateurId);

        boolean EnableUtilisateur(int utilisateurId);

}

