package ci.orange.digital.center.ahazzran.webservice.entities;

import javax.persistence.*;

@Entity
@Table(name="utilisateurs")
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int utilisateurId;
    
    private String email;
    private String statut;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public Utilisateur() {
        super();
    }


    public int getUtilisateurId() {
        return utilisateurId;
    }
    public void setUtilisateurId(int utilisateurId) {
        this.utilisateurId = utilisateurId;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }


    public Role getRole() {
        return role;
    }


    public void setRole(Role role) {
        this.role = role;
    }


    public String getStatut() {
        return statut;
    }


    public void setStatut(String statut) {
        this.statut = statut;
    }
    
}

