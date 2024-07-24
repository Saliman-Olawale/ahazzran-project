package ci.orange.digital.center.ahazzran.webservice.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ci.orange.digital.center.ahazzran.webservice.dtos.CreateOrUpdateRolePermissionInputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.CreateOrUpdateRolePermissionOutputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.PermissionOutputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.RoleOutputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.RolePermissionOutputDto;
import ci.orange.digital.center.ahazzran.webservice.entities.Permission;
import ci.orange.digital.center.ahazzran.webservice.entities.Role;
import ci.orange.digital.center.ahazzran.webservice.entities.RolePermission;
import ci.orange.digital.center.ahazzran.webservice.repositories.PermissionRepository;
import ci.orange.digital.center.ahazzran.webservice.repositories.RolePermissionRepository;
import ci.orange.digital.center.ahazzran.webservice.repositories.RoleRepository;

@Service

public class RolePermissionService implements IRolePermissionService{
    
    private RolePermissionRepository rolePermissionRepository;
    private RoleRepository roleRepository;
    private PermissionRepository permissionRepository;

    public RolePermissionService(RolePermissionRepository rolePermissionRepository, RoleRepository roleRepository, PermissionRepository permissionRepository){
        this.rolePermissionRepository = rolePermissionRepository;
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    @Override
    public RolePermissionOutputDto getPermissionsByRoleId(int roleId) {
        
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Pas de Role existant avec cet id " + roleId));

        List<RolePermission> rolePermissions = rolePermissionRepository.findByRoleRoleId(roleId);

        if (rolePermissions.isEmpty()) {
            throw new RuntimeException("Le rôle avec l'ID " + roleId + " n'a pas de permissions associées.");
        }

        return convertToRolePermissionOutputDto(role, rolePermissions);
        
    }

    public RolePermissionOutputDto convertToRolePermissionOutputDto(Role role, List<RolePermission> rolePermissions) {

        List<PermissionOutputDto> permissionOutputDtos = convertToPermissionOutputDto(rolePermissions);
        RoleOutputDto roleOutputDto = new RoleOutputDto(role);
        return new RolePermissionOutputDto(roleOutputDto, permissionOutputDtos);
    }

    public List<PermissionOutputDto> convertToPermissionOutputDto(List<RolePermission> rolePermissions) {

        return rolePermissions.stream()
                              .map(rolePermission -> new PermissionOutputDto(rolePermission.getPermission()))
                              .collect(Collectors.toList());
    }

    @Override
    public CreateOrUpdateRolePermissionOutputDto createOrUpdateRolePermission(CreateOrUpdateRolePermissionInputDto input) {

        try {

            RolePermission rolePermission = new RolePermission();
            rolePermission.setStatut("ENABLE");

            if (input.getId() > 0) {
                rolePermission = rolePermissionRepository.findById(input.getId()).orElseThrow(() -> new IllegalStateException("Pas de Role - Permission avec cet" + input.getId()));
            }

            Role role = roleRepository.findById(input.getId()).orElseThrow();          
            Permission permission = permissionRepository.findById(input.getPermissionId()).orElseThrow();

            rolePermission.setRole(role);
            rolePermission.setPermission(permission);

            rolePermissionRepository.save(rolePermission);
        } catch (Exception e) {
            return new CreateOrUpdateRolePermissionOutputDto("Erreur", e.getMessage());
        }

        return new CreateOrUpdateRolePermissionOutputDto("Succès", "L'association Role - Permission est un succès");

    }

    @Override
    public boolean disableRolePermission(int rolePermisionId) {

        try {
            RolePermission  rolePermission = rolePermissionRepository.findById(rolePermisionId).orElseThrow(() -> new IllegalStateException("Aucun Role - Permission trouvé avec cet id: " + rolePermisionId));
            rolePermission.setStatut("DISABLE");

            rolePermissionRepository.save(rolePermission);
           } catch (Exception e) { 
               return false;
           }
   
           return true;
    }

    @Override
    public boolean enableRolePermission(int rolePermisionId) {

        try {
            RolePermission  rolePermission = rolePermissionRepository.findById(rolePermisionId).orElseThrow(() -> new IllegalStateException("Aucun Role - Permission trouvé avec cet id: " + rolePermisionId));
            rolePermission.setStatut("ENABLE");

            rolePermissionRepository.save(rolePermission);
           } catch (Exception e) { 
               return false;
           }
   
           return true;
    }

   
}
