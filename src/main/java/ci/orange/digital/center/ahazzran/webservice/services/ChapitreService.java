package ci.orange.digital.center.ahazzran.webservice.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ci.orange.digital.center.ahazzran.webservice.dtos.ChapitreCoursDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.ChapitreOutputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.CreateOrUpdateChapitreInputDto;
import ci.orange.digital.center.ahazzran.webservice.entities.Chapitre;
import ci.orange.digital.center.ahazzran.webservice.repositories.ChapitreRepository;
import ci.orange.digital.center.ahazzran.webservice.services.ChapitreService;


@Service
public class ChapitreService implements IChapitreService{

    private ChapitreRepository chapitreRepository;

    public ChapitreService(final ChapitreRepository chapitreRepository) {
        this.chapitreRepository = chapitreRepository;
    }

    @Override
    public List<ChapitreOutputDto> getAllChapitres() {

        List<Chapitre> chapitre = chapitreRepository.findAll();

        return chapitre.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private ChapitreOutputDto convertToDTO(Chapitre chapitre) {
        return new ChapitreOutputDto(chapitre);
    }
    
    @Override
    public boolean CreateOrUpdateChapitre(CreateOrUpdateChapitreInputDto input) {

       try {
            Chapitre chapitre = new Chapitre();
            chapitre.setStatut("ENABLE");

            List<Chapitre> chapitres = chapitreRepository.findAll();
            if (input.getChapitreId() > 0) {
                chapitre = chapitreRepository.findById(input.getChapitreId()).orElseThrow();
            }else {
                for (Chapitre l : chapitres) {
                    if (l.getCodeChapitre().equals(input.getCodeChapitre())) {
                        return false;
                    }
                }
            }
            chapitre.setCodeChapitre(input.getCodeChapitre());
            chapitre.setNomChapitre(input.getNomChapitre());
            chapitreRepository.save(chapitre);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public boolean DisableChapitre(int chapitreId) {
        try {
            Chapitre  chapitre = chapitreRepository.findById(chapitreId).orElseThrow();
            chapitre.setStatut("DISABLE");
            chapitreRepository.save(chapitre);
           } catch (Exception e) {   
            return false;
           }
           return true;
    }

    @Override
    public boolean EnableChapitre(int chapitreId) {

        try {
            Chapitre  chapitre = chapitreRepository.findById(chapitreId).orElseThrow();
            chapitre.setStatut("ENABLE");
            chapitreRepository.save(chapitre);
            } catch (Exception e) {
            return false;
            }
            return true;
    }

    @Override
    public List<ChapitreCoursDto> getChapitresWithCours() {
        List<Chapitre> chapitres = chapitreRepository.findAll();

        return chapitres.stream().map(chapitre -> new ChapitreCoursDto(
                chapitre,
                chapitre.getCours().stream().map(cours -> new ChapitreCoursDto.CoursDto(cours))
                .collect(Collectors.toList())
        )).collect(Collectors.toList());
    }
}
