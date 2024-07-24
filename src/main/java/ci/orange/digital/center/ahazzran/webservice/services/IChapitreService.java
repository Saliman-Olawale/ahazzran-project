package ci.orange.digital.center.ahazzran.webservice.services;

import java.util.List;

import ci.orange.digital.center.ahazzran.webservice.dtos.ChapitreCoursDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.ChapitreOutputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.CreateOrUpdateChapitreInputDto;


public interface IChapitreService {

    List<ChapitreCoursDto> getChapitresWithCours();
    
    List<ChapitreOutputDto> getAllChapitres();

    boolean CreateOrUpdateChapitre(CreateOrUpdateChapitreInputDto input);

    boolean DisableChapitre(int chapitreId);

    boolean EnableChapitre(int chapitreId);
} 

