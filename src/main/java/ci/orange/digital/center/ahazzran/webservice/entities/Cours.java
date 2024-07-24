package ci.orange.digital.center.ahazzran.webservice.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cours")
public class Cours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int coursId;

    private String codeCours;
    private String nomCours;
    private String statut;

    @OneToMany(mappedBy = "cours")
    private List<ProgressionCours> progressionCours;

    @ManyToOne
    @JoinColumn(name = "chapitre_id")
    private Chapitre chapitre;

    @OneToMany(mappedBy = "cours")
    private List<CoursDetail> coursDetails;

    public Cours() {
        super();
    }

    public int getCoursId() {
        return coursId;
    }

    public void setCoursId(int coursId) {
        this.coursId = coursId;
    }

    public String getCodeCours() {
        return codeCours;
    }

    public void setCodeCours(String codeCours) {
        this.codeCours = codeCours;
    }

    public String getNomCours() {
        return nomCours;
    }

    public void setNomCours(String nomCours) {
        this.nomCours = nomCours;
    }

    public Chapitre getChapitre() {
        return chapitre;
    }

    public void setChapitre(Chapitre chapitre) {
        this.chapitre = chapitre;
    }

    public List<CoursDetail> getCoursDetails() {
        return coursDetails;
    }

    public void setCoursDetails(List<CoursDetail> coursDetails) {
        this.coursDetails = coursDetails;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public List<ProgressionCours> getProgressionCours() {
        return progressionCours;
    }

    public void setProgressionCours(List<ProgressionCours> progressionCours) {
        this.progressionCours = progressionCours;
    }

}
