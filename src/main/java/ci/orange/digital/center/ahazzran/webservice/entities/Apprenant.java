package ci.orange.digital.center.ahazzran.webservice.entities;

import java.util.List;

import javax.persistence.*;


@Entity
@Table(name="apprenants")
public class Apprenant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int apprenantId;
     
    private String nom;

    private String prenom;

    private String email;

    private String motDePasse;

    private String telephone;


    @OneToMany(mappedBy = "apprenant") 
    private List<Progression> progression;

    @ManyToOne
    @JoinColumn(name = "langue_id")
    private Langue langue;

    @ManyToOne
    @JoinColumn(name = "niveau_id")
    private Niveau niveau;


    public Apprenant() {
        super();
    }

    public int getApprenantId() {
        return apprenantId;
    }
    public void setApprenantId(int apprenantId) {
        this.apprenantId = apprenantId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public List<Progression> getProgression() {
        return progression;
    }

    public void setProgression(List<Progression> progression) {
        this.progression = progression;
    }

    
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Langue getLangue() {
        return langue;
    }

    public void setLangue(Langue langue) {
        this.langue = langue;
    }

    public Niveau getNiveau() {
        return niveau;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }

    
    
}
