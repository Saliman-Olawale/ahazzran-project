package ci.orange.digital.center.ahazzran.webservice.dtos;

import java.util.List;

import ci.orange.digital.center.ahazzran.webservice.entities.ProgressionChapitre;

public class ProgressionHebdoDto {

    private int chapitreId;
    private String chapitreNom;
    private boolean estTermine;
    private List<CoursStatutDto> coursStatuts;

    

    public ProgressionHebdoDto(ProgressionChapitre chapitre, List<CoursStatutDto> coursStatuts) {
        this.chapitreId = chapitre.getChapitre().getChapitreId();
        this.chapitreNom = chapitre.getChapitre().getNomChapitre();
        this.estTermine = chapitre.isEstTermine();
        this.coursStatuts = coursStatuts;
    }

    public int getChapitreId() {
        return chapitreId;
    }

    public void setChapitreId(int chapitreId) {
        this.chapitreId = chapitreId;
    }

    public String getChapitreNom() {
        return chapitreNom;
    }

    public void setChapitreNom(String chapitreNom) {
        this.chapitreNom = chapitreNom;
    }

    public boolean isEstTermine() {
        return estTermine;
    }

    public void setEstTermine(boolean estTermine) {
        this.estTermine = estTermine;
    }

    public List<CoursStatutDto> getCoursStatuts() {
        return coursStatuts;
    }

    public void setCoursStatuts(List<CoursStatutDto> coursStatuts) {
        this.coursStatuts = coursStatuts;
    }
}
