package ci.orange.digital.center.ahazzran.webservice.services;

import java.util.List;

import ci.orange.digital.center.ahazzran.webservice.dtos.CreateOrUpdateRoleInputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.CreateOrUpdateRoleOutputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.RoleOutputDto;

public interface IRoleService {

    List<RoleOutputDto> getAllRoles();

    CreateOrUpdateRoleOutputDto createOrUpdateRole(CreateOrUpdateRoleInputDto input);

    boolean disableRole(int roleId);

    boolean enableRole(int roleId);

        
}
