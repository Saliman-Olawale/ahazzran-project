package ci.orange.digital.center.ahazzran.webservice.entities;
import javax.persistence.*;

@Entity
@Table(name="niveau_details")
public class NiveauDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int niveauDetailId; 
    private int ordre;

    @ManyToOne
    @JoinColumn(name = "niveau_id")
    private Niveau niveau;

    @ManyToOne
    @JoinColumn(name = "chapitre_id")
    private Chapitre chapitre;

    public int getNiveauDetailId() {
        return niveauDetailId;
    }

    public void setNiveauDetailId(int niveauDetailId) {
        this.niveauDetailId = niveauDetailId;
    }

    public int getOrdre() {
        return ordre;
    }

    public void setOrdre(int ordre) {
        this.ordre = ordre;
    }

    public Niveau getNiveau() {
        return niveau;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }

    public Chapitre getChapitre() {
        return chapitre;
    }

    public void setChapitre(Chapitre chapitre) {
        this.chapitre = chapitre;
    }


    
}
