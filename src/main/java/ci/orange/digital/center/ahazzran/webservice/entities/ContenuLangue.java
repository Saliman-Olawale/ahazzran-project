package ci.orange.digital.center.ahazzran.webservice.entities;

import javax.persistence.*;


@Entity
@Table(name="contenu_de_langues")
public class ContenuLangue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contenuLangueId;
    private String mot;
    private String audio;
    private String statut;
    
    @ManyToOne
    @JoinColumn(name = "langue_id")
    private Langue langue;

    @ManyToOne
    @JoinColumn(name = "dictionnaire_id")
    private Dictionnaire dictionnaire;

    public ContenuLangue() {
        super();
    }

    public int getContenuLangueId() {
        return contenuLangueId;
    }

    public void setContenuLangueId(int contenuLangueId) {
        this.contenuLangueId = contenuLangueId;
    }

    public Langue getLangue() {
        return langue;
    }

    public void setLangue(Langue langue) {
        this.langue = langue;
    }

    public Dictionnaire getDictionnaire() {
        return this.dictionnaire;
    }

    public void setDictionnaire(Dictionnaire dictionnaire) {
        this.dictionnaire = dictionnaire;
    }

    public String getMot() {
        return mot;
    }

    public void setMot(String mot) {
        this.mot = mot;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}
