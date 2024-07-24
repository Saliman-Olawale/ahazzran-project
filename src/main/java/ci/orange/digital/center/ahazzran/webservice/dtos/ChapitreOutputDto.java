package ci.orange.digital.center.ahazzran.webservice.dtos;

import ci.orange.digital.center.ahazzran.webservice.entities.Chapitre;

public class ChapitreOutputDto {
    private int chapitreId;
    private String nomChapitre;
    private String codeChapitre;
    private boolean active;


     
    public ChapitreOutputDto(Chapitre chapitre) {
        chapitreId = chapitre.getChapitreId();
        nomChapitre = chapitre.getNomChapitre();
        codeChapitre = chapitre.getCodeChapitre();
        active = chapitre.getStatut().equals("ENABLE");
    }

    public String getNomChapitre() {
        return nomChapitre;
    }
    public String getCodeChapitre() {
        return codeChapitre;
    }
    
    public int getChapitreId() {
        return chapitreId;
    }

    public boolean isActive() {
        return active;
    }

    
}

