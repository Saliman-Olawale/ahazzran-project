package ci.orange.digital.center.ahazzran.webservice.entities;

import javax.persistence.*;


@Entity
@Table(name = "role_permissions")
public class RolePermission {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rolePermissionId;
    private String statut;
    
   @ManyToOne
   @JoinColumn(name="role_id")
   private Role role;

    @ManyToOne
    @JoinColumn(name = "permission_id")
    private Permission permission;


    public RolePermission() {
        super();
    }

    public int getRolePermissionId() {
        return rolePermissionId;
    }

    public void setRolePermissionId(int rolePermissionId) {
        this.rolePermissionId = rolePermissionId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    
    
}
