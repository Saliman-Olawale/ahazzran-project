package ci.orange.digital.center.ahazzran.webservice.dtos;

import java.util.List;



public class RolePermissionOutputDto {

    private RoleOutputDto role;
    private List<PermissionOutputDto> permissions;
   

    public RolePermissionOutputDto(RoleOutputDto role, List<PermissionOutputDto> permissions) {
        this.role = role;
        this.permissions = permissions;
       
    }

    public RoleOutputDto getRole() {
        return role;
    }

    public List<PermissionOutputDto> getPermissions() {
        return permissions;
    } 
}
