package ci.orange.digital.center.ahazzran.webservice.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="cours_details_templates")
public class CoursDetailTemplate {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int coursDetailTemplateId;

    private String code;
    private String libelle;

    @OneToMany(mappedBy = "coursDetailTemplate")
    private List<CoursDetail> coursDetails;


    @OneToMany(mappedBy = "coursDetailTemplate")
    private List<ExerciceChapitre> exerciceChapitres;


    @OneToMany(mappedBy = "coursDetailTemplate")
    private List<Evaluation> evaluations;



    public int getCoursDetailTemplateId() {
        return coursDetailTemplateId;
    }

    public void setCoursDetailTemplateId(int coursDetailTemplateId) {
        this.coursDetailTemplateId = coursDetailTemplateId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public List<CoursDetail> getCoursDetails() {
        return coursDetails;
    }

    public void setCoursDetails(List<CoursDetail> coursDetails) {
        this.coursDetails = coursDetails;
    }

    

}
