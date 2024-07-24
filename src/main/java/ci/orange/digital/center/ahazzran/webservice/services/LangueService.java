package ci.orange.digital.center.ahazzran.webservice.services;

import ci.orange.digital.center.ahazzran.webservice.dtos.CreateOrUpdateLangueInputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.LangueMobileOutputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.LangueOutputDto;
import ci.orange.digital.center.ahazzran.webservice.entities.Langue;
import ci.orange.digital.center.ahazzran.webservice.repositories.LangueRepository;
import ci.orange.digital.center.ahazzran.webservice.services.LangueService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class LangueService implements ILangueService {

    private LangueRepository langueRepository;

    public LangueService(final LangueRepository langueRepository) {
        this.langueRepository = langueRepository;
    }

    @Override
    public List<LangueOutputDto> getAllLangues() {

        List<Langue> langues = langueRepository.findAll();

        return langues.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<LangueMobileOutputDto> getAllLanguesEnable() {

        List<Langue> langues = langueRepository.findByStatut("ENABLE");
        

        return langues.stream().map(this::convertToLangueMobileOutputDto).collect(Collectors.toList());
    }

    @Override
    public boolean CreateOrUpdateLangue(CreateOrUpdateLangueInputDto input) {

        try {

            Langue langue = new Langue();
            langue.setStatut("ENABLE");

            List<Langue> langues = langueRepository.findAll();


            if (input.getId() > 0) {
                langue = langueRepository.findById(input.getId()).orElseThrow();
            }else {
                for (Langue l : langues) {
                    if (l.getCodeLangue().equals(input.getCode())) {
                        return false;
                    }
                }
            }
            

            langue.setCodeLangue(input.getCode());
            langue.setNom(input.getNom());
            
            langueRepository.save(langue);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public boolean DisableLangue(int langueId) {
        try {
         Langue   langue = langueRepository.findById(langueId).orElseThrow();
         langue.setStatut("DISABLE");
         langueRepository.save(langue);
        } catch (Exception e) { return false;
        }

        return true;
      
    }

    @Override
    public boolean EnableLangue(int langueId) {
        try {
            Langue   langue = langueRepository.findById(langueId).orElseThrow();
            langue.setStatut("ENABLE");
            langueRepository.save(langue);
           } catch (Exception e) { return false;
           }
   
           return true;
    }

    private LangueOutputDto convertToDTO(Langue langue) {
        return new LangueOutputDto(langue);
    }

    private LangueMobileOutputDto convertToLangueMobileOutputDto(Langue langue) {
        return new LangueMobileOutputDto(langue);
    }

}

