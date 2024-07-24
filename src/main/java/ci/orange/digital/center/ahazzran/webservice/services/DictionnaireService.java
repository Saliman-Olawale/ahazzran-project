package ci.orange.digital.center.ahazzran.webservice.services;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ci.orange.digital.center.ahazzran.webservice.dtos.CreateOrUpdateDicoInputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.DictionnaireOutputDto;
import ci.orange.digital.center.ahazzran.webservice.entities.Dictionnaire;
import ci.orange.digital.center.ahazzran.webservice.repositories.DictionnaireRepository;

@Service
public class DictionnaireService implements IDictionnaireService{

    private DictionnaireRepository dictionnaireRepository;
    private  UploadFileService uploadFileService;

    public DictionnaireService(final DictionnaireRepository dictionnaireRepository, final UploadFileService uploadFileService) {
        this.dictionnaireRepository = dictionnaireRepository;
        this.uploadFileService = uploadFileService;
    }

    @Override
    public List<DictionnaireOutputDto> getAllDictionnaire() {
        List<Dictionnaire> dictionnaires = dictionnaireRepository.findAll();

        return dictionnaires.stream().map(this::convertToDTO).collect(Collectors.toList());
    }



    @Override
    public boolean CreateOrUpdateDictionnaire(CreateOrUpdateDicoInputDto input, MultipartFile file) {
        try {
            Dictionnaire dictionnaire = new Dictionnaire();
            dictionnaire.setStatut("ENABLE");
            
            if (input.getDictionnaireId() > 0) {
                dictionnaire = dictionnaireRepository.findById(input.getDictionnaireId()).orElseThrow();
            }
            
            dictionnaire.setMot(input.getMot());
            dictionnaire.setDictionnaireType(input.getDictionnaireType());
            
            if (file != null && !file.isEmpty()) {
                String response = uploadFileService.uploadFile(file);
                
            dictionnaire.setImage(response);
            } else {
                return false;
            }
          

            dictionnaireRepository.save(dictionnaire);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }


    @Override
    public boolean DisableDictionnaire(int dictionnaireId) {
        try {
            Dictionnaire   dictionnaire = dictionnaireRepository.findById(dictionnaireId).orElseThrow();
            dictionnaire.setStatut("DISABLE");
            dictionnaireRepository.save(dictionnaire);
           } catch (Exception e) { return false;
           }
   
           return true;
    }

    @Override
    public boolean EnableDictionnaire(int dictionnaireId) {
        try {
            Dictionnaire dictionnaire = dictionnaireRepository.findById(dictionnaireId).orElseThrow();
            dictionnaire.setStatut("ENABLE");
            dictionnaireRepository.save(dictionnaire);
           } catch (Exception e) { return false;
           }
   
           return true;
    }
    private DictionnaireOutputDto convertToDTO(Dictionnaire dictionnaire) {
        return new DictionnaireOutputDto(dictionnaire);
    }

}
