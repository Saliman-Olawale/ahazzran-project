package ci.orange.digital.center.ahazzran.webservice.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ci.orange.digital.center.ahazzran.webservice.dtos.ContenuLangueOutputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.CreateOrUpdateContenuLangueOutputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.LangueGroupDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.LangueOutputDto;
import ci.orange.digital.center.ahazzran.webservice.entities.ContenuLangue;
import ci.orange.digital.center.ahazzran.webservice.entities.Dictionnaire;
import ci.orange.digital.center.ahazzran.webservice.entities.Langue;
import ci.orange.digital.center.ahazzran.webservice.repositories.ContenuLangueRepository;
import ci.orange.digital.center.ahazzran.webservice.repositories.DictionnaireRepository;
import ci.orange.digital.center.ahazzran.webservice.repositories.LangueRepository;
import ci.orange.digital.center.ahazzran.webservice.dtos.CreateOrUpdateContenuLangueInputDto;


@Service
public class ContenuLangueService implements IContenuLangueService {

    private ContenuLangueRepository contenuLangueRepository;

    private DictionnaireRepository dictionnaireRepository;

    private LangueRepository langueRepository;
    private  UploadFileService uploadFileService;



    public ContenuLangueService(ContenuLangueRepository contenuLangueRepository,
            DictionnaireRepository dictionnaireRepository, LangueRepository langueRepository, UploadFileService uploadFileService) {
        this.contenuLangueRepository = contenuLangueRepository;
        this.dictionnaireRepository = dictionnaireRepository;
        this.langueRepository = langueRepository;
        this.uploadFileService = uploadFileService;
    }


    public LangueGroupDto getContenusByLangueId(int langueId) {

        List<ContenuLangue> contenus = contenuLangueRepository.findByLangueLangueId(langueId);

        Langue langue = langueRepository.findById(langueId).orElseThrow(() -> new RuntimeException("Pas de langue existante avec cet id " + langueId));
       

        if (langue != null && contenus.isEmpty()) {
            contenus = new ArrayList<>();
        }

        return convertToLangueGroupDTO(contenus, langue);
    }

    private LangueGroupDto convertToLangueGroupDTO(List<ContenuLangue> contenuLangue, Langue langue) {

        List<ContenuLangueOutputDto> contenuLangueOutputDtoList = contenuLangue.stream()
            .map(ctl -> new ContenuLangueOutputDto(ctl)) 
            .collect(Collectors.toList());

        LangueOutputDto langueOutputDto = new LangueOutputDto(langue);

        return new LangueGroupDto(langueOutputDto, contenuLangueOutputDtoList);
    }

    

    @Override
    public CreateOrUpdateContenuLangueOutputDto CreateOrUpdateContenuLangue(CreateOrUpdateContenuLangueInputDto input, MultipartFile file) {
        CreateOrUpdateContenuLangueOutputDto result;

        try { 
            ContenuLangue contenuLangue;

            if (input.getId() > 0) {
                contenuLangue = contenuLangueRepository.findById(input.getId()).orElseThrow(
                        () -> new RuntimeException("ContenuLangue non trouvé avec l'ID " + input.getId()));
            } else {
                contenuLangue = new ContenuLangue();
                contenuLangue.setStatut("ENABLE");
            }

            Langue langue = langueRepository.findById(input.getLangueId()).orElseThrow(
                () -> new RuntimeException("Aucun Langue associée a cet langueId :" + input.getLangueId()));

            Dictionnaire dictionnaire = dictionnaireRepository.findById(input.getDictionnaireId()).orElseThrow(
                () -> new RuntimeException("Aucun Dictionnaire associée a cet " + input.getDictionnaireId()));

            if (file != null && !file.isEmpty()) {
                String response = uploadFileService.uploadFile(file);

                contenuLangue.setAudio(response);
            } else {
                return  new CreateOrUpdateContenuLangueOutputDto("Echec", "Votre fichier est vide ou incompatible");
            }
                      

            contenuLangue.setLangue(langue);
            contenuLangue.setDictionnaire(dictionnaire);
            contenuLangue.setMot(input.getMot());
            contenuLangueRepository.save(contenuLangue);

            result = new CreateOrUpdateContenuLangueOutputDto("OK", "ContenuLangue créé avec succès"); 
        }catch(Exception e) {
            result = new CreateOrUpdateContenuLangueOutputDto("Echec", "Aucun ajout ou modification apporté a un contenu de Langue. Erreur :" + e.getLocalizedMessage());
        }

        return result;
    }
    @Override
    public boolean DisableContenuLangue(int id) {
       try {
        
        ContenuLangue contenuLangue = contenuLangueRepository.findById(id).orElseThrow();
        contenuLangue.setStatut("DISABLE");
        contenuLangueRepository.save(contenuLangue);
        } catch (Exception e){
         return false;
        }

        return true;
    }


    @Override
    public boolean EnableContenuLangue(int id) {
        try {
            ContenuLangue contenuLangue = contenuLangueRepository.findById(id).orElseThrow();
            contenuLangue.setStatut("ENABLE");
            contenuLangueRepository.save(contenuLangue);
           } catch (Exception e) { return false;
           }
   
           return true;
    }

}
