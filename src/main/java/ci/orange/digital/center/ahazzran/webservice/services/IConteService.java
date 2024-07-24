package ci.orange.digital.center.ahazzran.webservice.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import ci.orange.digital.center.ahazzran.webservice.dtos.ConteOutputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.CreateOrUpdateConteDto;

public interface IConteService {
    List<ConteOutputDto> getAllConteByLangueId(int langueId);

    List<ConteOutputDto> getAllConteByLangueIdBackOffice(int langueId);

    boolean CreateOrUpdateConte(CreateOrUpdateConteDto input, MultipartFile file);

    boolean DisableConte(int id);

    boolean EnableConte(int id);

}
