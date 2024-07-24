package ci.orange.digital.center.ahazzran.webservice.entities;

import java.util.List;
import javax.persistence.*;


@Entity
@Table(name = "roles")
public class Role {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleId;
    
    private String designation;
    private String codeRole;
    private String statut;

    @OneToMany(mappedBy = "role")
    private List<Utilisateur> utilisateurs;

    @OneToMany(mappedBy = "role")
    private List<RolePermission> rolePermissions;


    public int getRoleId() {
        return roleId;
    }


    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }


    public String getDesignation() {
        return designation;
    }


    public void setDesignation(String designation) {
        this.designation = designation;
    }


    public String getCodeRole() {
        return codeRole;
    }


    public void setCodeRole(String codeRole) {
        this.codeRole = codeRole;
    }


    public List<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }


    public void setUtilisateurs(List<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }


    public String getStatut() {
        return statut;
    }


    public void setStatut(String statut) {
        this.statut = statut;
    }


    public List<RolePermission> getRolePermissions() {
        return rolePermissions;
    }


    public void setRolePermissions(List<RolePermission> rolePermissions) {
        this.rolePermissions = rolePermissions;
    }
    
    
}
