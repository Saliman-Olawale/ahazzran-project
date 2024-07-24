package ci.orange.digital.center.ahazzran.webservice.dtos;

import ci.orange.digital.center.ahazzran.webservice.entities.Niveau;

public class NiveauOutputDto {
    
    private int niveauId; 
    private String nom;
    private int ordre;
    private double pointMin;
    private double pointMax;
    private boolean active;

    public NiveauOutputDto(Niveau niveau) {
        niveauId = niveau.getNiveauId();
        nom = niveau.getNom();
        ordre = niveau.getOrdre();
        pointMin = niveau.getPointMin();
        pointMax = niveau.getPointMax();
        active = niveau.getStatut() != null ? niveau.getStatut().equals("ENABLE") : false;
    }

    public int getNiveauId() {
        return niveauId;
    }

    public String getNom() {
        return nom;
    }

    public int getOrdre() {
        return ordre;
    }

    public double getPointMin() {
        return pointMin;
    }

    public double getPointMax() {
        return pointMax;
    }

    public boolean isActive() {
        return active;
    }

    
    
}
