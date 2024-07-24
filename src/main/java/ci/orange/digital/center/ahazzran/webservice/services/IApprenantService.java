package ci.orange.digital.center.ahazzran.webservice.services;

import ci.orange.digital.center.ahazzran.webservice.dtos.ChangerMotDePasseInputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.ConnexionOutputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.EditerProfilInputDto;

public interface IApprenantService {
    ConnexionOutputDto editProfil(EditerProfilInputDto input);
    ConnexionOutputDto changeMotDePasse(ChangerMotDePasseInputDto input);
}
