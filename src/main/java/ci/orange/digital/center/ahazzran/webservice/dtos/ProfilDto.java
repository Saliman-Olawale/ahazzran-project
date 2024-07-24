package ci.orange.digital.center.ahazzran.webservice.dtos;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import ci.orange.digital.center.ahazzran.webservice.entities.Progression;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfilDto {
    
    private int id;
    private String nom;
    private String prenoms;
    private String email;
    private String langue;
    private int nbreDePoints;
    private String niveau;
    private String telephone;
    private Map<String, Integer> xp;

    private int nbreDeJoursActivite;
    private List<ProgressionHebdoDto> progressionsHebdosParLangue;

    public ProfilDto(Progression progression,
            int nbreDeJoursActivite,Map<String, Integer> recompenseOutput) {
                this.id = progression.getApprenant().getApprenantId();
                this.nom = progression.getApprenant().getNom();
                this.prenoms = progression.getApprenant().getPrenom();
                this.email = progression.getApprenant().getEmail();
                this.langue = progression.getLangue().getNom();
                this.telephone = progression.getApprenant().getTelephone();
                this.nbreDePoints = progression.getXp();
                this.niveau = progression.getNiveau().getNom();
                this.nbreDeJoursActivite = nbreDeJoursActivite;
                this.xp = recompenseOutput;
            }

    

    public String getNom() {
        return nom;
    }

    public String getPrenoms() {
        return prenoms;
    }

    public String getEmail() {
        return email;
    }

    public int getNbreDePoints() {
        return nbreDePoints;
    }

    public String getNiveau() {
        return niveau;
    }

    public int getNbreDeJoursActivite() {
        return nbreDeJoursActivite;
    }

    public String getLangue() {
        return langue;
    }
    public List<ProgressionHebdoDto> getProgressionsHebdosParLangue() {
        return progressionsHebdosParLangue;
    }

    public String getTelephone() {
        return telephone;
    }
    
    public int getId() {
        return id;
    }

    public Map<String, Integer> getXp() {
        return xp;
    }

    
   
}
