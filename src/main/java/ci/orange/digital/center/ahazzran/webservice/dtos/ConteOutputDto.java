package ci.orange.digital.center.ahazzran.webservice.dtos;

import ci.orange.digital.center.ahazzran.webservice.entities.Conte;

public class ConteOutputDto {
    
    private String nom;
    private String contenuConte;
    private String audioConte;
    private boolean active;

    public ConteOutputDto(Conte conte){
        this.nom = conte.getNom();
        this.contenuConte = conte.getContenuConte();
        this.audioConte = conte.getAudioConte();
        this.active = conte.getStatut()!= null? conte.getStatut().equals("ENABLE") : false;

    }

    public String getNom() {
        return nom;
    }

    public String getContenuConte() {
        return contenuConte;
    }

    public String getAudioConte() {
        return audioConte;
    }

    public boolean isActive() {
        return active;
    }

        
}

