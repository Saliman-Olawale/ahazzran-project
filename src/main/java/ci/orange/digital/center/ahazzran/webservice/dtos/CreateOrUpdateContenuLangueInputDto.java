package ci.orange.digital.center.ahazzran.webservice.dtos;

public class CreateOrUpdateContenuLangueInputDto {
    
    private int id;
    private String mot;
    private int langueId;
    private int dictionnaireId;

    public int getId() {
        return id;
    }
    public String getMot() {
        return mot;
    }
    public int getLangueId() {
        return langueId;
    }
    public int getDictionnaireId() {
        return dictionnaireId;
    }
    
}
