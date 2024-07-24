package ci.orange.digital.center.ahazzran.webservice.dtos;

import java.util.List;

public class ReponsesAuxTemplateInputDto {
    
    private int coursId;
    private List<TemplateReponseDto> data;

    public ReponsesAuxTemplateInputDto() {
        super();
    }

    public int getCoursId() {
        return coursId;
    }

    public List<TemplateReponseDto> getData() {
        return data;
    }
}

