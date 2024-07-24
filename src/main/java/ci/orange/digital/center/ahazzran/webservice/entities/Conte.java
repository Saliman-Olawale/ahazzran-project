package ci.orange.digital.center.ahazzran.webservice.entities;

import javax.persistence.*;

@Entity
@Table(name="contes")
public class Conte {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int conteId;
    
    private String nom;
    private String contenuConte;
    private String statut;

    @ManyToOne
    @JoinColumn(name = "langue_id")
    private Langue langue;

    @Lob
    private String audioConte;

    public Conte() {
        super();
    }
    public int getConteId() {
        return conteId;
    }
    public void setConteId(int conteId) {
        this.conteId = conteId;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
   
    public Langue getLangue() {
        return langue;
    }
    public void setLangue(Langue langue) {
        this.langue = langue;
    }
    public String getAudioConte() {
        return audioConte;
    }
    public void setAudioConte(String audioConte) {
        this.audioConte = audioConte;
    }
    public String getContenuConte() {
        return contenuConte;
    }
    public void setContenuConte(String contenuConte) {
        this.contenuConte = contenuConte;
    }
    public String getStatut() {
        return statut;
    }
    public void setStatut(String statut) {
        this.statut = statut;
    }

    

}
