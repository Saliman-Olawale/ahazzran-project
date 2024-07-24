package ci.orange.digital.center.ahazzran.webservice.dtos;


public class CreateOrUpdateDicoInputDto {

    private int dictionnaireId;

    private String dictionnaireType;

    private String mot;

    
    public CreateOrUpdateDicoInputDto() {
    }

    public int getDictionnaireId() {
        return dictionnaireId;
    }

    public String getDictionnaireType() {
        return dictionnaireType;
    }

    public String getMot() {
        return mot;
    }

    

}
