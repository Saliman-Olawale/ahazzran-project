package ci.orange.digital.center.ahazzran.webservice.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;

public class TemplateReponseDto {
    
    private int templateId;

    @JsonProperty("reponses")
    private Object reponses;

    public TemplateReponseDto() {
    }

    public int getTemplateId() {
        return templateId;
    }

    public Object getReponses() {
        return reponses;
    }

}
