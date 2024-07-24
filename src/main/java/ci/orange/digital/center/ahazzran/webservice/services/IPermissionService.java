package ci.orange.digital.center.ahazzran.webservice.services;

import java.util.List;

import ci.orange.digital.center.ahazzran.webservice.dtos.PermissionOutputDto;

public interface IPermissionService {

    List<PermissionOutputDto> getAllPermissions();
    
}
