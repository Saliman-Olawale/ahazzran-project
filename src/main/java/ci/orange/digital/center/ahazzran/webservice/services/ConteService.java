package ci.orange.digital.center.ahazzran.webservice.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.multipart.MultipartFile;

import org.springframework.stereotype.Service;

import ci.orange.digital.center.ahazzran.webservice.dtos.ConteOutputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.CreateOrUpdateConteDto;
import ci.orange.digital.center.ahazzran.webservice.entities.Conte;
import ci.orange.digital.center.ahazzran.webservice.repositories.ConteRepository;

@Service
public class ConteService implements  IConteService{

    private ConteRepository conteRepository;
    private  UploadFileService uploadFileService;


    public ConteService(final ConteRepository conteRepository,final UploadFileService uploadFileService) {
        this.conteRepository = conteRepository;
        this.uploadFileService = uploadFileService;
    }

    @Override
    public List<ConteOutputDto> getAllConteByLangueId(int langueId) {
       
        List<Conte> contes = conteRepository.findByStatutAndLangueLangueId("ENABLE", langueId);

        if (contes.isEmpty()) {
            throw new RuntimeException("Aucun conte associé a l'id de cette langue : " + langueId);
        }

        List<ConteOutputDto> contesOuputDto = convertToContenuOutputDto(contes);

        return contesOuputDto;
    }

    public List<ConteOutputDto> convertToContenuOutputDto(List<Conte> contes){

        return contes.stream().map(conte -> new ConteOutputDto(conte)).collect(Collectors.toList());
    }

    @Override
    public boolean CreateOrUpdateConte(CreateOrUpdateConteDto input, MultipartFile file) {

       try {

            Conte conte = new Conte();
            conte.setStatut("ENABLE");

            if (input.getId() > 0) {
                conte = conteRepository.findById(input.getId()).orElseThrow();
            }

             
            if (file != null && !file.isEmpty()) {
                String response = uploadFileService.uploadFile(file);
                    
                conte.setAudioConte(response);
                } else {
                    return false;
                }
            
            conte.setNom(input.getNom());
            conte.setContenuConte(input.getContenuConte());
            
            conteRepository.save(conte);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public boolean DisableConte(int id) {

        try {
            Conte conte = conteRepository.findById(id).orElseThrow();
            conte.setStatut("DISABLE");
            conteRepository.save(conte);
           } catch (Exception e) { return false;
           }
   
           return true;
         
    }

    @Override
    public boolean EnableConte(int id) {

        try {
            Conte conte = conteRepository.findById(id).orElseThrow();
            conte.setStatut("ENABLE");
            conteRepository.save(conte);
           } catch (Exception e) { return false;
           }
   
           return true;
    }

    @Override
    public List<ConteOutputDto> getAllConteByLangueIdBackOffice(int langueId) {

        List<Conte> contes = conteRepository.findAllByLangueLangueId(langueId);

        if (contes.isEmpty()) {
            throw new RuntimeException("Aucun conte associé a l'id de cette langue : " + langueId);
        }

        List<ConteOutputDto> contesOuputDto = convertToOutputBackOfficeDto(contes);

        return contesOuputDto;
    }

    public List<ConteOutputDto> convertToOutputBackOfficeDto(List<Conte> contes){

        return contes.stream().map(conte -> new ConteOutputDto(conte)).collect(Collectors.toList());
    }

    
}
