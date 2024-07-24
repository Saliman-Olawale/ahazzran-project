package ci.orange.digital.center.ahazzran.webservice.entities;

import javax.persistence.*;


@Entity
@Table(name="contenu_de_exercices")
public class ContenuExercice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contenuExerciceId;

    @ManyToOne
    @JoinColumn(name="exercice_chapitre_id")
    private ExerciceChapitre exerciceChapitre;

    @ManyToOne
    @JoinColumn(name="dictionnaire_id")
    private Dictionnaire dictionnaire;



    public ContenuExercice() {
        super();
    }

    public ExerciceChapitre getExerciceChapitre() {
        return exerciceChapitre;
    }

    public void setExerciceChapitre(ExerciceChapitre exerciceChapitre) {
        this.exerciceChapitre = exerciceChapitre;
    }

    public Dictionnaire getDictionnaire() {
        return dictionnaire;
    }

    public void setDictionnaire(Dictionnaire dictionnaire) {
        this.dictionnaire = dictionnaire;
    }

    public int getContenuExerciceId() {
        return contenuExerciceId;
    }

    public void setContenuExerciceId(int contenuExerciceId) {
        this.contenuExerciceId = contenuExerciceId;
    }

    
       
}
