package ci.orange.digital.center.ahazzran.webservice.dtos;

import ci.orange.digital.center.ahazzran.webservice.entities.Permission;

public class PermissionOutputDto {

    private int permissionId;
    
    private String intitule;
    private String codePermission;
    private boolean active;

    public PermissionOutputDto(Permission permission){
        this.permissionId = permission.getPermissionId();
        this.intitule = permission.getIntitule();
        this.codePermission = permission.getCodePermission();
        this.active = permission.getStatut() != null ? permission.getStatut().equals("ENABLE") : false;
    }


    public int getPermissionId() {
        return permissionId;
    }

    public String getIntitule() {
        return intitule;
    }

    public String getCodePermission() {
        return codePermission;
    }

    public boolean isActive() {
        return active;
    }

    

}
