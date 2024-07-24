package ci.orange.digital.center.ahazzran.webservice.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name="progression_chapitre")
public class ProgressionChapitre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int progressionChapitreId;
    
    private Timestamp debutChapitre;
    private Timestamp finChapitre;
    private boolean estTermine;
    private boolean aReprendre;
    

    @ManyToOne
    @JoinColumn(name = "progression_id")
    private Progression progression;

    @ManyToOne
    @JoinColumn(name = "chapitre_id")
    private Chapitre chapitre;


    @OneToMany(mappedBy = "progressionChapitre")
    private List<ProgressionCours> progressionCours;

    public ProgressionChapitre(){
        super();
    }

   
    public Timestamp getDebutChapitre() {
        return debutChapitre;
    }

    public void setDebutChapitre(Timestamp debutChapitre) {
        this.debutChapitre = debutChapitre;
    }

    public Timestamp getFinChapitre() {
        return finChapitre;
    }

    public void setFinChapitre(Timestamp finChapitre) {
        this.finChapitre = finChapitre;
    }

    public boolean isEstTermine() {
        return estTermine;
    }

    public void setEstTermine(boolean estTermine) {
        this.estTermine = estTermine;
    }

    public int getProgressionChapitreId() {
        return progressionChapitreId;
    }

    public void setProgressionChapitreId(int progressionId) {
        this.progressionChapitreId = progressionId;
    }


    public Progression getProgression() {
        return progression;
    }


    public void setProgression(Progression progression) {
        this.progression = progression;
    }


    public Chapitre getChapitre() {
        return chapitre;
    }


    public void setChapitre(Chapitre chapitre) {
        this.chapitre = chapitre;
    }


    public List<ProgressionCours> getProgressionCours() {
        return progressionCours;
    }


    public void setProgressionCours(List<ProgressionCours> progressionCours) {
        this.progressionCours = progressionCours;
    }


    public boolean isaReprendre() {
        return aReprendre;
    }


    public void setaReprendre(boolean aReprendres) {
        this.aReprendre = aReprendres;
    } 
    
}
