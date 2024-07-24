package ci.orange.digital.center.ahazzran.webservice.entities;

import javax.persistence.*;

@Entity
@Table(name="contenu_de_evaluations")
public class ContenuEvaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contenuEvaluationId;
    
    @ManyToOne
    @JoinColumn(name="dictionnaire_id")
    private Dictionnaire dictionnaire;

    @ManyToOne
    @JoinColumn(name="evaluation_id")
    private Evaluation evalutation;
    

    public ContenuEvaluation() {
        super();
    }

    public int getContenuEvaluationId() {
        return contenuEvaluationId;
    }

    public void setContenuEvaluationId(int contenuEvaluationId) {
        this.contenuEvaluationId = contenuEvaluationId;
    }

    public Evaluation getEvalutation() {
        return evalutation;
    }

    public void setEvalutation(Evaluation evalutation) {
        this.evalutation = evalutation;
    }

    public Dictionnaire getDictionnaire() {
        return dictionnaire;
    }


    public void setDictionnaire(Dictionnaire dictionnaire) {
        this.dictionnaire = dictionnaire;
    }
}
