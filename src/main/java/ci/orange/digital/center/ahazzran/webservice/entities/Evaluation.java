package ci.orange.digital.center.ahazzran.webservice.entities;

import javax.persistence.*;

@Entity
@Table(name="evaluations")
public class Evaluation implements ICoursDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int evalutationId;

    private int recompense;
    private int id_contenuLangue;
    private int coursId;



    @ManyToOne
    @JoinColumn(name = "cours_detail_template_id")
    private CoursDetailTemplate coursDetailTemplate;

    public Evaluation() {
        super();
    }
    
    public int getEvalutationId() {
        return evalutationId;
    }

    public void setEvalutationId(int evalutationId) {
        this.evalutationId = evalutationId;
    }

    public void setRecompense(int points) {
        this.recompense = points;
    }

    public CoursDetailTemplate getCoursDetailTemplate() {
        return coursDetailTemplate;
    }

    public void setCoursDetailTemplate(CoursDetailTemplate coursDetailTemplate) {
        this.coursDetailTemplate = coursDetailTemplate;
    }

    public void setContenuLangueId(int contenuLangueId) {
        this.id_contenuLangue = contenuLangueId;
    }


    public void setCours_id(int cours_id) {
        this.coursId = cours_id;
    }

    @Override
    public int getRecompense() {
        return this.recompense;
    }

    @Override
    public int getId() {
        return this.coursDetailTemplate.getCoursDetailTemplateId();

    }

    @Override
    public String getLibelle() {
        return this.coursDetailTemplate.getLibelle();
    }

    @Override
    public int getId_contenuLangue() {
        return this.id_contenuLangue;

    }

    public int getCoursId() {
        return coursId;
    }   
    
}
