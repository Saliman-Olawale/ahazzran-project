package ci.orange.digital.center.ahazzran.webservice.dtos;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class NiveauInfo {
    
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Optional<Integer> niveauId;

    private String nom;
    
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private boolean estTermine;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private boolean aReprendre;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private boolean pasEncoreDebuter;


    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Optional<Integer> points;

    public NiveauInfo(int niveauId, String nom) {
        this.niveauId = Optional.of(niveauId);
        this.nom = nom;
    }

    public NiveauInfo(int points,boolean estTermine,boolean aReprendre) {
        this.points = Optional.of(points);
        this.estTermine = estTermine;
        this.aReprendre = aReprendre;
    }

    public NiveauInfo(int points, boolean pasEncoreDebuter) {

        this.points = Optional.of(points);
        this.pasEncoreDebuter = pasEncoreDebuter;
    }

    public Optional<Integer> getNiveauId() {
        return niveauId;
    }

    public String getNom() {
        return nom;
    }

    public Optional<Integer> getPoints() {
        return points;
    }

    public boolean isEstTermine() {
        return estTermine;
    }

    public boolean isaReprendre() {
        return aReprendre;
    }

    public boolean isPasEncoreDebuter() {
        return pasEncoreDebuter;
    }  

    
    
}
