package ci.orange.digital.center.ahazzran.webservice.dtos;

import ci.orange.digital.center.ahazzran.webservice.entities.ContenuLangue;

public class ContenuLangueOutputDto {
    
    private int id;
    private int dictionnaireId;
    private String mot;
    private String audio;
    private boolean active;


    public ContenuLangueOutputDto(ContenuLangue contenuLangue){
        id = contenuLangue.getContenuLangueId();
        dictionnaireId = contenuLangue.getDictionnaire() != null ? contenuLangue.getDictionnaire().getDictionnaireId() : 0;
        mot = contenuLangue.getMot();
        audio = contenuLangue.getAudio();
        active = contenuLangue.getStatut() != null ? contenuLangue.getStatut().equals("ENABLE") : false;

    }

    public int getId() {
        return id;
    }

    public int getDictionnaireId() {
        return dictionnaireId;
    }

    public String getMot() {
        return mot;
    }

    public String getAudio() {
        return audio;
    }

    public boolean isActive() {
        return active;
    }

    
}
