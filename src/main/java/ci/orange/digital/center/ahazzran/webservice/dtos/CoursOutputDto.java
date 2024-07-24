package ci.orange.digital.center.ahazzran.webservice.dtos;

import ci.orange.digital.center.ahazzran.webservice.entities.Cours;

public class CoursOutputDto {

    private int id;
    private String codeCours;
    private String nomCours;
    private boolean active;

    public CoursOutputDto(Cours cours) {
        this.id = cours.getCoursId();
        this.codeCours = cours.getCodeCours();
        this.nomCours = cours.getNomCours();
        this.active = cours.getStatut() != null ? cours.getStatut().equals("ENABLE") : false;
 
    }

    public int getId() {
        return id;
    }

    public String getCodeCours() {
        return codeCours;
    }


    public String getNomCours() {
        return nomCours;
    }


    public boolean isActive() {
        return active;
    }
   
    
}
