package ci.orange.digital.center.ahazzran.webservice.entities;
import java.util.List;
import javax.persistence.*;


@Entity
@Table(name="niveaux")
public class Niveau {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int niveauId; 
    private String nom;
    private int ordre;
    private int pointMin;
    private int pointMax;

    private String statut;

    @OneToMany(mappedBy="niveau")
    private List<NiveauDetail> niveauDetails;
    
    @OneToMany(mappedBy="niveau")
    private List<Progression> progressions;

    @OneToMany(mappedBy="niveau")
    private List<Apprenant> apprenant;


    public int getNiveauId() {
        return niveauId;
    }

    public void setNiveauId(int niveauId) {
        this.niveauId = niveauId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getOrdre() {
        return ordre;
    }

    public void setOrdre(int ordre) {
        this.ordre = ordre;
    }

    public List<NiveauDetail> getNiveauDetails() {
        return niveauDetails;
    }

    public void setNiveauDetails(List<NiveauDetail> niveauDetails) {
        this.niveauDetails = niveauDetails;
    }

    public double getPointMin() {
        return pointMin;
    }

    public void setPointMin(int pointMin) {
        this.pointMin = pointMin;
    }

    public double getPointMax() {
        return pointMax;
    }

    public void setPointMax(int pointMax) {
        this.pointMax = pointMax;
    }

    public String getStatut() {
        return statut;
    }

    public List<Progression> getProgressions() {
        return progressions;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public void setProgressions(List<Progression> progressions) {
        this.progressions = progressions;
    }

    
    
}
