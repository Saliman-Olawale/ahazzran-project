package ci.orange.digital.center.ahazzran.webservice.dtos;

import ci.orange.digital.center.ahazzran.webservice.entities.Langue;

public class LangueMobileOutputDto {
    private int langueId;
    private String nom;
    private String codeLangue;
   

    public LangueMobileOutputDto(Langue langue) {
      langueId = langue.getLangueId();
      nom = langue.getNom();    
      codeLangue = langue.getCodeLangue();
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
}
