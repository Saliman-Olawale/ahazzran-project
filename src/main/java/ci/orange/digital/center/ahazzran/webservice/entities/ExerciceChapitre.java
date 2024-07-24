package ci.orange.digital.center.ahazzran.webservice.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="exercice_de_chapitres")
public class ExerciceChapitre implements ICoursDetail{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int exerciceChapitreId;
    
    private float temps;
    private int recompense;
    private int id_contenuLangue;

    @ManyToOne
    @JoinColumn(name = "cours_detail_template_id")
    private CoursDetailTemplate coursDetailTemplate;

    @ManyToOne
    @JoinColumn(name="chapitre_id")
    private Chapitre chapitre;

    
    @OneToMany(mappedBy="exerciceChapitre")
    private List<ContenuExercice> contenuExercices;


    public ExerciceChapitre() {
        super();
    }
    

    public int getExerciceChapitreId() {
        return exerciceChapitreId;
    }


    public void setExerciceChapitreId(int exerciceChapitreId) {
        this.exerciceChapitreId = exerciceChapitreId;
    }


    public Chapitre getChapitre() {
        return chapitre;
    }


    public void setChapitre(Chapitre chapitre) {
        this.chapitre = chapitre;
    }

    public void setRecompense(int recompenseExercice) {
        this.recompense = recompenseExercice;
    }


    public float getTemps() {
        return temps;
    }


    public void setTemps(float temps) {
        this.temps = temps;
    }


    public void setCoursDetailTemplate(CoursDetailTemplate coursDetailTemplate) {
        this.coursDetailTemplate = coursDetailTemplate;
    }


    public List<ContenuExercice> getContenuExercices() {
        return contenuExercices;
    }


    public void setContenuExercices(List<ContenuExercice> contenuExercices) {
        this.contenuExercices = contenuExercices;
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

    @Override
    public int getRecompense() {
        return recompense;
    }

    @Override
    public CoursDetailTemplate getCoursDetailTemplate() {
        return this.coursDetailTemplate;
    }

    public void setId_contenuLangue(int id_contenuLangue) {
        this.id_contenuLangue = id_contenuLangue;
    }

}
