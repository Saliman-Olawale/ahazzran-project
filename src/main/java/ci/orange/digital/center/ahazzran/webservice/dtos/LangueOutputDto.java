package ci.orange.digital.center.ahazzran.webservice.dtos;

import ci.orange.digital.center.ahazzran.webservice.entities.Langue;

public class LangueOutputDto {
    private int langueId;
    private String nom;
    private String codeLangue;
    private boolean active;

    public LangueOutputDto(Langue langue) {
        langueId = langue.getLangueId();
        nom = langue.getNom();
        codeLangue = langue.getCodeLangue();
        active = langue.getStatut() != null ? langue.getStatut().equals("ENABLE") : false;
    }

    public int getLangueId() {
        return langueId;
    }

    public String getNom() {
        return nom;
    }

    public String getCodeLangue() {
        return codeLangue;
    }

    public boolean isActive() {
        return active;
    }
}
