package ci.orange.digital.center.ahazzran.webservice.dtos;

public class CreateOrUpdateChapitreInputDto {
    
    private int chapitreId;
    private String nomChapitre;
    private String codeChapitre;


    public String getNomChapitre() {
        return nomChapitre;
    }
    public String getCodeChapitre() {
        return codeChapitre;
    }
    
    public int getChapitreId() {
        return chapitreId;
    }
}
