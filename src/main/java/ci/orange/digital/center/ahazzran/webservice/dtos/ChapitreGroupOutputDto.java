package ci.orange.digital.center.ahazzran.webservice.dtos;

import java.util.List;

public class ChapitreGroupOutputDto {
    
    private ChapitreOutputDto chapitre;
    private List<CoursOutputDto> cours;

    public ChapitreGroupOutputDto(ChapitreOutputDto chapitre, List<CoursOutputDto> cours) {
        this.chapitre = chapitre;
        this.cours = cours;
    }

    public ChapitreOutputDto getChapitre() {
        return chapitre;
    }

    public List<CoursOutputDto> getCours() {
        return cours;
    }

    
    
}
