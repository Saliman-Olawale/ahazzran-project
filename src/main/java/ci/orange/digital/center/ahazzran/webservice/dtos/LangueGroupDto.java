package ci.orange.digital.center.ahazzran.webservice.dtos;

import java.util.List;

public class LangueGroupDto {
    
    private LangueOutputDto langue;
    private List<ContenuLangueOutputDto> contenus;

    public LangueGroupDto(LangueOutputDto langue, List<ContenuLangueOutputDto> contenus) {
        this.langue = langue;
        this.contenus = contenus;
    }

    public LangueOutputDto getLangue() {
        return langue;
    }

    public List<ContenuLangueOutputDto> getContenus() {
        return contenus;
    }
}
