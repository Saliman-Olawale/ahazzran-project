package ci.orange.digital.center.ahazzran.webservice.entities;

import javax.persistence.*;

@Entity
@Table(name = "contenu_de_cours_details")
public class ContenuCoursDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contenuCourdetailId;

    @ManyToOne
    @JoinColumn(name = "dictionnaire_id")
    private Dictionnaire dictionnaire;

    @ManyToOne
    @JoinColumn(name = "cours_detail_id")
    private CoursDetail coursDetail;

    public ContenuCoursDetail() {
        super();
    }

    public Dictionnaire getDictionnaire() {
        return dictionnaire;
    }

    public void setDictionnaire(Dictionnaire dictionnaire) {
        this.dictionnaire = dictionnaire;
    }

    public CoursDetail getCoursDetail() {
        return coursDetail;
    }

    public void setCoursDetail(CoursDetail coursDetail) {
        this.coursDetail = coursDetail;
    }

    public int getContenuCourdetailId() {
        return contenuCourdetailId;
    }

    public void setContenuCourdetailId(int contenuCourdetailId) {
        this.contenuCourdetailId = contenuCourdetailId;
    }

}
