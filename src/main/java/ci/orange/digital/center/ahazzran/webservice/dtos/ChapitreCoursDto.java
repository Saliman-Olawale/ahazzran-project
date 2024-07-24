package ci.orange.digital.center.ahazzran.webservice.dtos;

import java.util.List;

import ci.orange.digital.center.ahazzran.webservice.entities.Chapitre;
import ci.orange.digital.center.ahazzran.webservice.entities.Cours;

public class ChapitreCoursDto {

    private String nomChapitre;
    private String codeChapitre;
    private String image;
    private List<CoursDto> cours;

    public ChapitreCoursDto(Chapitre chapitre, List<CoursDto> cours) {
        this.nomChapitre = chapitre.getNomChapitre();
        this.codeChapitre =  chapitre.getCodeChapitre();
        this.image = chapitre.getImage();
        this.cours = cours;
    }

    public String getNomChapitre() {
        return nomChapitre;
    }


    public String getCodeChapitre() {
        return codeChapitre;
    }


    public List<CoursDto> getCours() {
        return cours;
    }


    public static class CoursDto {
        private int coursId;
        private String nomCours;

        public CoursDto(Cours cours) {
            this.coursId = cours.getCoursId();
            this.nomCours = cours.getNomCours();
        }

        public int getCoursId() {
            return coursId;
        }

        public String getNomCours() {
            return nomCours;
        }
    }

    public String getImage() {
        return image;
    }

    
}
