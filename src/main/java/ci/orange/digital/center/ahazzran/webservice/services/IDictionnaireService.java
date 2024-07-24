package ci.orange.digital.center.ahazzran.webservice.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import ci.orange.digital.center.ahazzran.webservice.dtos.CreateOrUpdateDicoInputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.DictionnaireOutputDto;

public interface IDictionnaireService {
        List<DictionnaireOutputDto> getAllDictionnaire();

        boolean CreateOrUpdateDictionnaire(CreateOrUpdateDicoInputDto input, MultipartFile file);

        boolean DisableDictionnaire(int dictionnaireId);

        boolean EnableDictionnaire(int dictionnaireId);

}
