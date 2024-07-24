package ci.orange.digital.center.ahazzran.webservice.dtos;

import javax.persistence.Lob;

import ci.orange.digital.center.ahazzran.webservice.entities.Dictionnaire;

public class DictionnaireOutputDto {

    private int dictionnaireId;
    private String dictionnaireType;

    private String mot;
    private boolean active;

    @Lob
    private String image;

    public DictionnaireOutputDto(Dictionnaire dictionnaire) {
        dictionnaireId = dictionnaire.getDictionnaireId();
        mot=dictionnaire.getMot();
        image = dictionnaire.getImage();
        active = dictionnaire.getStatut().equals("ENABLE");

    }

    public int getDictionnaireId() {
        return dictionnaireId;
    }

    public String getMot() {
        return mot;
    }

  
    public String getImage() {
        return image;
    }

    public boolean isActive() {
        return active;
    }

    public String getDictionnaireType() {
        return dictionnaireType;
    }

    
   
}
