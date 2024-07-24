package ci.orange.digital.center.ahazzran.webservice.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="dictionnaires")
public class Dictionnaire {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int dictionnaireId;

    private String dictionnaireType;
    private String mot;
    private String statut;


    @Lob
    private String image;

    @OneToMany(mappedBy = "dictionnaire")
    private List<ContenuLangue> contenuLangue;

    @OneToMany(mappedBy = "dictionnaire")
    private List<ContenuCoursDetail> contenuCoursDetails;

    @OneToMany(mappedBy = "dictionnaire")
    private List<ContenuExercice> contenuExercices;

    public int getDictionnaireId() {
        return dictionnaireId;
    }

    public void setDictionnaireId(int dictionnaireId) {
        this.dictionnaireId = dictionnaireId;
    }

    public String getMot() {
        return mot;
    }

    public void setMot(String mot) {
        this.mot = mot;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDictionnaireType() {
        return dictionnaireType;
    }

    public void setDictionnaireType(String dictionnaireType) {
        this.dictionnaireType = dictionnaireType;
    }

    public List<ContenuLangue> getContenuLangue() {
        return contenuLangue;
    }

    public void setContenuLangue(List<ContenuLangue> contenuLangue) {
        this.contenuLangue = contenuLangue;
    }

    public List<ContenuExercice> getContenuExercices() {
        return contenuExercices;
    }

    public void setContenuExercices(List<ContenuExercice> contenuExercices) {
        this.contenuExercices = contenuExercices;
    }

    public List<ContenuCoursDetail> getContenuCoursDetails() {
        return contenuCoursDetails;
    }

    public void setContenuCoursDetails(List<ContenuCoursDetail> contenuCoursDetails) {
        this.contenuCoursDetails = contenuCoursDetails;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }



}
