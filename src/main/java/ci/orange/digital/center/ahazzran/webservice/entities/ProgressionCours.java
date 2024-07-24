package ci.orange.digital.center.ahazzran.webservice.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "progression_cours")
public class ProgressionCours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int progressionCoursId;
    
    private Timestamp debutCours;
    private Timestamp finCours;
    private boolean estTermine;
    private boolean aReprendre;
    private int points;

    @ManyToOne
    @JoinColumn(name = "progression_chapitre_id")
    private ProgressionChapitre progressionChapitre;

    @ManyToOne
    @JoinColumn(name = "cours_id")
    private Cours cours;

    public ProgressionCours() {
        super();
    }

    public int getProgressionCoursId() {
        return progressionCoursId;
    }

    public void setProgressionCoursId(int progressionCoursId) {
        this.progressionCoursId = progressionCoursId;
    }

    public Timestamp getDebutCours() {
        return debutCours;
    }

    public void setDebutCours(Timestamp debutCours) {
        
        this.debutCours = debutCours;
    }

    public Timestamp getFinCours() {
        return finCours;
    }

    public void setFinCours(Timestamp finCours) {
        this.finCours = finCours;
    }

    public boolean isEstTermine() {
        return estTermine;
    }

    public void setEstTermine(boolean estTermine) {
        this.estTermine = estTermine;
    }

    public ProgressionChapitre getProgressionChapitre() {
        return progressionChapitre;
    }

    public void setProgressionChapitre(ProgressionChapitre progressionChapitre) {
        this.progressionChapitre = progressionChapitre;
    }

    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean isaReprendre() {
        return aReprendre;
    }

    public void setaReprendre(boolean aReprendre) {
        this.aReprendre = aReprendre;
    }

    
    
}
