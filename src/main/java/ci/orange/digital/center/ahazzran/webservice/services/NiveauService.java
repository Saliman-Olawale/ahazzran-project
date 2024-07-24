package ci.orange.digital.center.ahazzran.webservice.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ci.orange.digital.center.ahazzran.webservice.dtos.CreateOrUpdateNiveauInputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.CreateOrUpdateNiveauOutputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.NiveauOutputDto;
import ci.orange.digital.center.ahazzran.webservice.entities.Niveau;
import ci.orange.digital.center.ahazzran.webservice.repositories.NiveauRepository;



@Service
public class NiveauService implements INiveauService{

    private NiveauRepository niveauRepository;

    public NiveauService(final NiveauRepository niveauRepository) {
        this.niveauRepository = niveauRepository;
    }


    @Override
    public List<NiveauOutputDto> getAllNiveau() {

        List<Niveau> niveaux = niveauRepository.findAll();

        return niveaux.stream().map(this::convertToNiveauOutputDto).collect(Collectors.toList());
        
    }


    @Override
    public CreateOrUpdateNiveauOutputDto CreateOrUpdateNiveau(CreateOrUpdateNiveauInputDto input) {
        try {
            List<Niveau> niveaux = niveauRepository.findAll();

            Niveau niveau = new Niveau();

            niveau.setStatut("ENABLE");

            if (input.getNiveauId() > 0) {
                niveau = niveauRepository.findById(input.getNiveauId()).orElseThrow(() -> new IllegalStateException("Aucun Niveau avec cet Id" +input.getNiveauId() + "n'existe."));
            }

            for (Niveau nivea : niveaux){
                if (nivea.getNom().equals(input.getNom())){
                    return new CreateOrUpdateNiveauOutputDto("Erreur", "Un niveau existe deja avec ce nom: " + input.getNom());
                }
                if (input.getOrdre() ==0 && nivea.getOrdre() == input.getOrdre()){
                    return new CreateOrUpdateNiveauOutputDto("Erreur", "Un niveau existe deja avec cet ordre: " + input.getOrdre());
                }

                if (input.getPointMin() >= input.getPointMax() || nivea.getPointMax() >= input.getPointMin() ){
                    return new CreateOrUpdateNiveauOutputDto("Erreur", "Niveau existant : " + nivea.getNom() + " Point min : " + nivea.getPointMin() + " Point max : " + nivea.getPointMax());
                }

            } 
            niveau.setNom(input.getNom());
            niveau.setOrdre(input.getOrdre());
            niveau.setPointMin(input.getPointMin());
            niveau.setPointMax(input.getPointMax());

            niveauRepository.save(niveau);
        } catch (Exception e) {
            return new CreateOrUpdateNiveauOutputDto("Erreur", " Voici l'origine de l'erreur :"+ e.getLocalizedMessage());
        }

        return new CreateOrUpdateNiveauOutputDto("Succès", "Ajout ou modification d'un niveau.");
    }


    @Override
    public boolean DisableNiveau(int id) {

       try {
         Niveau  niveau = niveauRepository.findById(id).orElseThrow(() -> new IllegalStateException("Aucun niveau ne possede cet id: " + id));

         niveau.setStatut("DISABLE");     
         niveauRepository.save(niveau);
        } catch (Exception e) { 
            return false;
        }

        return true;
    }


    @Override
    public boolean EnableNiveau(int id) {
       
        try {
            Niveau  niveau = niveauRepository.findById(id).orElseThrow(() -> new IllegalStateException("Aucun niveau ne possede cet id: " + id));
            
            niveau.setStatut("ENABLE");
            niveauRepository.save(niveau);
           } catch (Exception e) { 
            return false;
           }

           return true;

    }

    public NiveauOutputDto convertToNiveauOutputDto(Niveau niveau){
        return new NiveauOutputDto(niveau);
    }
    
    @Override
    public String getNiveauByPoints(int points) {
        Niveau niveau = niveauRepository.findByPointMinLessThanEqualAndPointMaxGreaterThanEqual(points, points);

        return niveau.getNom();
    }
}
