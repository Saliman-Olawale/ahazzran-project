package ci.orange.digital.center.ahazzran.webservice.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ci.orange.digital.center.ahazzran.webservice.dtos.PermissionOutputDto;
import ci.orange.digital.center.ahazzran.webservice.entities.Permission;
import ci.orange.digital.center.ahazzran.webservice.repositories.PermissionRepository;

@Service
public class PermissionService implements IPermissionService{

    private PermissionRepository permissionRepository;

    public PermissionService(final PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public List<PermissionOutputDto> getAllPermissions() {

        List<Permission> permission = permissionRepository.findAll();

        List<PermissionOutputDto> permissions = convertToPermissionOutputDTO(permission);

        return permissions;
    }

    public List<PermissionOutputDto> convertToPermissionOutputDTO(List<Permission> permissions) {

        return permissions.stream().map(permission -> new PermissionOutputDto(permission)).collect(Collectors.toList());
    }
    
}
