package ci.orange.digital.center.ahazzran.webservice.entities;

import java.util.List;
import javax.persistence.*;

@Entity
@Table(name="langues")
public class Langue {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int langueId;
    
    private String nom;
    private String codeLangue;
    private String statut;

    @OneToMany(mappedBy="langue")
    private List<Progression> progressions;

    @OneToMany(mappedBy="langue")
    private List<ContenuLangue> contenuLangues;
    
    @OneToMany(mappedBy="langue")
    private List<Conte> contes;

    @OneToMany(mappedBy="langue")
    private List<Apprenant> apprenant;


    public Langue() {
        super();
    }

    public int getLangueId() {
        return langueId;
    }

    public void setLangueId(int langueId) {
        this.langueId = langueId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCodeLangue() {
        return codeLangue;
    }

    public void setCodeLangue(String codeLangue) {
        this.codeLangue = codeLangue;
    }

    public List<Progression> getProgressions() {
        return progressions;
    }

    public void setProgressions(List<Progression> progressions) {
        this.progressions = progressions;
    }

    public List<ContenuLangue> getContenuLangues() {
        return contenuLangues;
    }

    public void setContenuLangues(List<ContenuLangue> contenuLangues) {
        this.contenuLangues = contenuLangues;
    }

    public List<Conte> getContes() {
        return contes;
    }

    public void setContes(List<Conte> contes) {
        this.contes = contes;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
    
}
