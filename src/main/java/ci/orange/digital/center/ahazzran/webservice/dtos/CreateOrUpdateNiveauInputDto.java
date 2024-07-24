package ci.orange.digital.center.ahazzran.webservice.dtos;

public class CreateOrUpdateNiveauInputDto {

    private int niveauId; 
    private String nom;
    private int ordre;
    private int pointMin;
    private int pointMax;

    public CreateOrUpdateNiveauInputDto() {
    }
    public int getNiveauId() {
        return niveauId;
    }
    public String getNom() {
        return nom;
    }
    public int getOrdre() {
        return ordre;
    }
    public int getPointMin() {
        return pointMin;
    }
    public int getPointMax() {
        return pointMax;
    }

    
    
}
