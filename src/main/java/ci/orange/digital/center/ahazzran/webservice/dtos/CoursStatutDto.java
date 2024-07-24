package ci.orange.digital.center.ahazzran.webservice.dtos;

import java.time.LocalDateTime;
import java.time.ZoneId;

import ci.orange.digital.center.ahazzran.webservice.entities.ProgressionCours;

public class CoursStatutDto {

    private boolean estTermine;
    private LocalDateTime finalDate;
    private int point;

    public CoursStatutDto() {
    }

    public CoursStatutDto(ProgressionCours progressionCours) {
        this.point = progressionCours.getPoints();
        this.estTermine = progressionCours.isEstTermine();
        this.finalDate = progressionCours.getFinCours()!= null? LocalDateTime.ofInstant(progressionCours.getFinCours().toInstant(), ZoneId.systemDefault()) : null;
    }

    public int getPoint() {
        return point;
    }

    public boolean isEstTermine() {
        return estTermine;
    }

    public LocalDateTime getFinalDate() {
        return finalDate;
    }

   

    
}
