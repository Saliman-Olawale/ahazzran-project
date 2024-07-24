package ci.orange.digital.center.ahazzran.webservice.dtos;

import ci.orange.digital.center.ahazzran.webservice.entities.CoursDetailTemplate;

public class CoursDetailTemplateDto {

    private int coursDetailTemplateId;
    private String code;
    private String libelle;

    public CoursDetailTemplateDto(CoursDetailTemplate template) {
        this.coursDetailTemplateId = template.getCoursDetailTemplateId();
        this.code =  template.getCode();
        this.libelle = template.getLibelle();
    }

    // Getters and setters

    public int getCoursDetailTemplateId() {
        return coursDetailTemplateId;
    }


    public String getCode() {
        return code;
    }

    public String getLibelle() {
        return libelle;
    }

}
