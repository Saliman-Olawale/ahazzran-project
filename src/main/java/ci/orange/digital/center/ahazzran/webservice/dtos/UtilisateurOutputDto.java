package ci.orange.digital.center.ahazzran.webservice.dtos;

import ci.orange.digital.center.ahazzran.webservice.entities.Utilisateur;

public class UtilisateurOutputDto {

    private int utilisateurId;
    private String email;
    private String roleName;
    private boolean active;


    public UtilisateurOutputDto(Utilisateur utilisateur) {
        utilisateurId = utilisateur.getUtilisateurId();
        email = utilisateur.getEmail();
        active = utilisateur.getStatut() != null ?  utilisateur.getStatut().equals("ENABLE") : false;
        this.roleName = utilisateur.getRole() != null ? utilisateur.getRole().getDesignation() : "Aucun Role";
    }


    public int getUtilisateurId() {
        return utilisateurId;
    }


    public String getEmail() {
        return email;
    }


    public String getRoleName() {
        return roleName;
    }


    public boolean isActive() {
        return active;
    }

    
}
    
