package ci.orange.digital.center.ahazzran.webservice.services;

import java.util.List;

import ci.orange.digital.center.ahazzran.webservice.dtos.CreateOrUpdateNiveauInputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.CreateOrUpdateNiveauOutputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.NiveauOutputDto;

public interface INiveauService  {
    
    List<NiveauOutputDto> getAllNiveau();

    CreateOrUpdateNiveauOutputDto CreateOrUpdateNiveau(CreateOrUpdateNiveauInputDto input);

    boolean DisableNiveau(int id);

    boolean EnableNiveau(int id);

    String getNiveauByPoints(int points);
}
