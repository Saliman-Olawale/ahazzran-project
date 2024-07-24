package ci.orange.digital.center.ahazzran.webservice.services;

import java.util.List;

import ci.orange.digital.center.ahazzran.webservice.dtos.CreateOrUpdateLangueInputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.LangueMobileOutputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.LangueOutputDto;

public interface ILangueService {
        List<LangueOutputDto> getAllLangues();
        List<LangueMobileOutputDto> getAllLanguesEnable();

        boolean CreateOrUpdateLangue(CreateOrUpdateLangueInputDto input);

        boolean DisableLangue(int langueId);

        boolean EnableLangue(int langueId);

}
