package ci.orange.digital.center.ahazzran.webservice.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cours_details")

public class CoursDetail implements ICoursDetail{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCoursDetail;
    private int recompense;
    private int id_contenuLangue;

    @ManyToOne
    @JoinColumn(name = "cours_id")
    private Cours cours;

    @ManyToOne
    @JoinColumn(name = "cours_detail_template_id")
    private CoursDetailTemplate coursDetailTemplate;

    @OneToMany(mappedBy = "coursDetail")
    private List<ContenuCoursDetail> contenuCoursDetails;

    public CoursDetail() {
        super();
    }


    public void setCoursDetailId(int coursDetailId) {
        this.idCoursDetail = coursDetailId;
    }

    

    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    public List<ContenuCoursDetail> getContenuCours() {
        return contenuCoursDetails;
    }

    public void setContenuCours(List<ContenuCoursDetail> contenuCoursDetails) {
        this.contenuCoursDetails = contenuCoursDetails;
    }
  

    public void setRecompense(int recompense) {
        this.recompense = recompense;
    }

    public List<ContenuCoursDetail> getContenuCoursDetails() {
        return contenuCoursDetails;
    }

    public void setContenuCoursDetails(List<ContenuCoursDetail> contenuCoursDetails) {
        this.contenuCoursDetails = contenuCoursDetails;
    }

    public void setCoursDetailTemplate(CoursDetailTemplate coursDetailTemplate) {
        this.coursDetailTemplate = coursDetailTemplate;
    }
   

    public void setId_contenuLangue(int id_contenuLangue) {
        this.id_contenuLangue = id_contenuLangue;
    }

    public int getIdCoursDetail() {
        return idCoursDetail;
    }
    
    @Override
    public int getId() {
        return this.coursDetailTemplate.getCoursDetailTemplateId();
    }

    @Override
    public int getId_contenuLangue() {
        return id_contenuLangue;
    }

    @Override
    public String getLibelle() {
        return this.coursDetailTemplate.getLibelle();
    }

    @Override
    public int getRecompense() {
        return this.recompense;
    }

    @Override
    public CoursDetailTemplate getCoursDetailTemplate() {
        return this.coursDetailTemplate;
    }
}
