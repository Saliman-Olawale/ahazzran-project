package ci.orange.digital.center.ahazzran.webservice.services;


import org.springframework.web.multipart.MultipartFile;

import ci.orange.digital.center.ahazzran.webservice.dtos.CreateOrUpdateContenuLangueInputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.CreateOrUpdateContenuLangueOutputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.LangueGroupDto;


public interface IContenuLangueService {

    LangueGroupDto getContenusByLangueId(int langueId);
    
    CreateOrUpdateContenuLangueOutputDto CreateOrUpdateContenuLangue(CreateOrUpdateContenuLangueInputDto input, MultipartFile file);

    boolean DisableContenuLangue(int id);

    boolean EnableContenuLangue(int id);

}

