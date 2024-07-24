package ci.orange.digital.center.ahazzran.webservice.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "chapitres")

public class Chapitre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int chapitreId;

    private String nomChapitre;
    private String codeChapitre;
    private String statut;
    private String image;

    @OneToMany(mappedBy="chapitre")
    private List<ProgressionChapitre> progressionChapitre;

    @OneToMany(mappedBy = "chapitre")
    private List<ExerciceChapitre> exerciceChapitres;

    @OneToMany(mappedBy = "chapitre")
    private List<Cours> cours;

    @OneToMany(mappedBy = "chapitre")
    private List<NiveauDetail> niveauDetails;

    public Chapitre() {
        super();
    }

    public int getChapitreId() {
        return chapitreId;
    }

    public void setChapitreId(int chapitreId) {
        this.chapitreId = chapitreId;
    }

    public String getNomChapitre() {
        return nomChapitre;
    }

    public void setNomChapitre(String nomChapitre) {
        this.nomChapitre = nomChapitre;
    }

    public String getCodeChapitre() {
        return codeChapitre;
    }

    public void setCodeChapitre(String codeChapitre) {
        this.codeChapitre = codeChapitre;
    }

    public List<ExerciceChapitre> getExerciceChapitres() {
        return exerciceChapitres;
    }

    public void setExerciceChapitres(List<ExerciceChapitre> exerciceChapitres) {
        this.exerciceChapitres = exerciceChapitres;
    }

    public List<Cours> getCours() {
        return cours;
    }

    public void setCours(List<Cours> cours) {
        this.cours = cours;
    }

    public List<NiveauDetail> getNiveauDetails() {
        return niveauDetails;
    }

    public void setNiveauDetails(List<NiveauDetail> niveauDetails) {
        this.niveauDetails = niveauDetails;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<ProgressionChapitre> getProgressionChapitre() {
        return progressionChapitre;
    }

    public void setProgressionChapitre(List<ProgressionChapitre> progressionChapitre) {
        this.progressionChapitre = progressionChapitre;
    }

    
    
}
