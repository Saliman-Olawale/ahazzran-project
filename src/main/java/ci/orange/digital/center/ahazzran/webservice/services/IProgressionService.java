package ci.orange.digital.center.ahazzran.webservice.services;

import java.util.List;

import ci.orange.digital.center.ahazzran.webservice.dtos.ApprenantOuputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.ProfilDto;

public interface IProgressionService {

    List<ApprenantOuputDto> getApprenantsTriesParXpParLangue(int langueId);

    int calculerXpTotal();

    List<ApprenantOuputDto> getTop30ApprenantsTriesParXpParLangue(int langueId);
    
    int getRangApprenantParLangue(int apprenantId, int langueId);

    ProfilDto getWeeklyProgression(int apprenantId, int langueId) ;

}
