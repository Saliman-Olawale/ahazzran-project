package ci.orange.digital.center.ahazzran.webservice.services;

import ci.orange.digital.center.ahazzran.webservice.dtos.ChapitreGroupOutputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.CreateOrUpdateCoursInputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.CreateOrUpdateCoursOutputDto;

public interface ICoursService {
    
    ChapitreGroupOutputDto getCoursByChapitreId(int chapitreId);

    CreateOrUpdateCoursOutputDto CreateOrUpdateCours(CreateOrUpdateCoursInputDto input);

    boolean DisableCours(int id);

    boolean EnableCours(int coursId);
    
}
