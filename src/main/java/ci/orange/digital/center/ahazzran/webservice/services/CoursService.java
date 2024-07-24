package ci.orange.digital.center.ahazzran.webservice.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ci.orange.digital.center.ahazzran.webservice.dtos.ChapitreGroupOutputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.ChapitreOutputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.CoursOutputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.CreateOrUpdateCoursInputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.CreateOrUpdateCoursOutputDto;
import ci.orange.digital.center.ahazzran.webservice.entities.Chapitre;
import ci.orange.digital.center.ahazzran.webservice.entities.Cours;
import ci.orange.digital.center.ahazzran.webservice.repositories.ChapitreRepository;
import ci.orange.digital.center.ahazzran.webservice.repositories.CoursRepository;

@Service
public class CoursService implements ICoursService{

    private CoursRepository coursRepository;
    private ChapitreRepository chapitreRepository;

    public CoursService(final CoursRepository coursRepository, final ChapitreRepository chapitreRepository) {
        this.coursRepository = coursRepository;
        this.chapitreRepository = chapitreRepository;
    }
    @Override
    public ChapitreGroupOutputDto getCoursByChapitreId(int chapitreId) {

        List<Cours> cours = coursRepository.findByChapitreChapitreId(chapitreId);

        Chapitre chapitre = chapitreRepository.findById(chapitreId).orElseThrow();

        if (chapitre == null) {
            return null; 
        }
        if (chapitre!= null && cours.isEmpty()) {
            cours = new ArrayList<>();
        }

        return convertToChapitreGroupOutputDto(chapitre, cours);
    }


    private ChapitreGroupOutputDto convertToChapitreGroupOutputDto(Chapitre chapitre, List<Cours> cours){

        List<CoursOutputDto> coursOutputDto = cours.stream().map(ctl -> new CoursOutputDto(ctl)).collect(Collectors.toList());
        
        ChapitreOutputDto chapitreOutputDto = new ChapitreOutputDto(chapitre);

        return new ChapitreGroupOutputDto(chapitreOutputDto, coursOutputDto);
    }

    @Override
    public CreateOrUpdateCoursOutputDto CreateOrUpdateCours(CreateOrUpdateCoursInputDto input) {
        CreateOrUpdateCoursOutputDto result;
        try {

            Cours cours;
            if (input.getId() > 0) {
                 cours = coursRepository.findById(input.getId()).orElseThrow(() -> new RuntimeException("Aucun cours ne possede cet Id " + input.getId()));

            } else {
                cours = new Cours();
                cours.setStatut("ENABLE");
            }

            Chapitre chapitre = chapitreRepository.findById(input.getChapitreId()).orElseThrow(() -> new RuntimeException("Aucun chapitre ne possede cet Id " + input.getChapitreId()));
            
            cours.setChapitre(chapitre);
            cours.setCodeCours(input.getCodeCours());
            cours.setNomCours(input.getNomCours());

            coursRepository.save(cours);

            result = new CreateOrUpdateCoursOutputDto("OK", "La création d'un cours est un succès");

        }catch (Exception e){
            result = new CreateOrUpdateCoursOutputDto("Echec", "Aucun ajout ou modification apporté a un Cours. Erreur :" + e.getLocalizedMessage());

        }

        return result;
    }
    @Override
    public boolean DisableCours(int id) {
      
        try {

            Cours cours = coursRepository.findById(id).orElseThrow(() -> new IllegalStateException());
            cours.setStatut("DISABLE");
            coursRepository.save(cours);

           } catch (Exception e) { 
            return false;
           }

           return true;
    }

    @Override
    public boolean EnableCours(int coursId) {
       try {
        
        Cours cours = coursRepository.findById(coursId).orElseThrow(() -> new IllegalStateException());
        cours.setStatut("ENABLE");
        coursRepository.save(cours);

       } catch (Exception e) {
        return false;
       }
       return true;

    }
       
}
