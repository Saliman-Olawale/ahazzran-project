package ci.orange.digital.center.ahazzran.webservice.services;



import ci.orange.digital.center.ahazzran.webservice.dtos.CreateOrUpdateRolePermissionInputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.CreateOrUpdateRolePermissionOutputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.RolePermissionOutputDto;

public interface IRolePermissionService {
    
    RolePermissionOutputDto getPermissionsByRoleId(int roleId);

    CreateOrUpdateRolePermissionOutputDto createOrUpdateRolePermission(CreateOrUpdateRolePermissionInputDto input);

    boolean disableRolePermission(int rolePermisionId);

    boolean enableRolePermission(int rolePermisionId);

}
