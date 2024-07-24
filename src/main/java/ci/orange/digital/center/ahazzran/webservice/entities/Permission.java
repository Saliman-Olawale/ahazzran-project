package ci.orange.digital.center.ahazzran.webservice.entities;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name="permissions")
public class Permission {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int permissionId;
    
    private String intitule;
    private String codePermission;
    private String statut;

    @OneToMany(mappedBy = "permission")
    private List<RolePermission> rolePermissions;

    public Permission() {
        super();
    }

    public int getPermissionId() {
        return permissionId;
    }
    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }
    public String getIntitule() {
        return intitule;
    }
    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }
    public String getCodePermission() {
        return codePermission;
    }
    public void setCodePermission(String codePermission) {
        this.codePermission = codePermission;
    }

    public List<RolePermission> getRolePermissions() {
        return rolePermissions;
    }

    public void setRolePermissions(List<RolePermission> rolePermissions) {
        this.rolePermissions = rolePermissions;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    
}
