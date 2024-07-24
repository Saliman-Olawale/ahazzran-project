package ci.orange.digital.center.ahazzran.webservice.dtos;

public class CreateOrUpdateCoursInputDto {
    
    private int id;
    private String codeCours;
    private String nomCours;
    private int chapitreId;


    
    public String getCodeCours() {
        return codeCours;
    }
    public String getNomCours() {
        return nomCours;
    }
    public int getChapitreId() {
        return chapitreId;
    }
    public int getId() {
        return id;
    }
    
}



