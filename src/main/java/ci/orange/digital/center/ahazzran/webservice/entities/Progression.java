package ci.orange.digital.center.ahazzran.webservice.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="progressions")
public class Progression {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int progressionId;
    
    private int xp;

    @ManyToOne
    @JoinColumn(name = "apprenant_id")
    private Apprenant apprenant;

    @ManyToOne
    @JoinColumn(name = "langue_id")
    private Langue langue;


    @ManyToOne
    @JoinColumn(name = "niveau_id")
    private Niveau niveau;

    @OneToMany(mappedBy = "progression")
    private List<ProgressionChapitre> progressionChapitres;

    public Progression() {
        super();
    }

    public int getProgressionId() {
        return progressionId;
    }

    public void setProgressionId(int progressionId) {
        this.progressionId = progressionId;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public Langue getLangue() {
        return langue;
    }

    public void setLangue(Langue langue) {
        this.langue = langue;
    }

    public List<ProgressionChapitre> getProgressionChapitres() {
        return progressionChapitres;
    }

    public void setProgressionChapitres(List<ProgressionChapitre> progressionChapitres) {
        this.progressionChapitres = progressionChapitres;
    }

    public Apprenant getApprenant() {
        return apprenant;
    }

    public void setApprenant(Apprenant apprenant) {
        this.apprenant = apprenant;
    }

    public Niveau getNiveau() {
        return niveau;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }

      
}
