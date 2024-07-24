package ci.orange.digital.center.ahazzran.webservice.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ci.orange.digital.center.ahazzran.webservice.dtos.CreateOrUpdateRoleInputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.CreateOrUpdateRoleOutputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.RoleOutputDto;
import ci.orange.digital.center.ahazzran.webservice.entities.Role;
import ci.orange.digital.center.ahazzran.webservice.repositories.RoleRepository;

@Service
public class RoleService implements IRoleService {

    private RoleRepository roleRepository;

    public RoleService(final RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<RoleOutputDto> getAllRoles() {
       
        List<Role> roles = roleRepository.findAll();

        return roles.stream().map(this::convertToRoleOutputDto).collect(Collectors.toList());
    }


    @Override
    public CreateOrUpdateRoleOutputDto createOrUpdateRole(CreateOrUpdateRoleInputDto input) {
       
        try {

            Role role = new Role();
            role.setStatut("ENABLE");

            if (input.getId() > 0) {
                role = roleRepository.findById(input.getId()).orElseThrow();
            }
            role.setDesignation(input.getDesignation());
            role.setCodeRole(input.getCodeRole());

            roleRepository.save(role);
        } catch (Exception e) {
            return new CreateOrUpdateRoleOutputDto("Erreur", e.getMessage());
        }

        return new CreateOrUpdateRoleOutputDto("Succès", "Role créé avec succès");

    }

    @Override
    public boolean disableRole(int roleId) {
       
        try {
         Role  role = roleRepository.findById(roleId).orElseThrow(() -> new IllegalStateException("Aucun role trouvé avec cet id: " + roleId));
         role.setStatut("DISABLE");
         roleRepository.save(role);
        } catch (Exception e) { 
            return false;
        }

        return true;

    }

    @Override
    public boolean enableRole(int roleId) {
        
        try {
            Role  role = roleRepository.findById(roleId).orElseThrow();
            role.setStatut("ENABLE");
            roleRepository.save(role);
           } catch (Exception e) { 
               return false;
           }
   
           return true;

    }

    public RoleOutputDto convertToRoleOutputDto(Role role){
        return new RoleOutputDto(role);
    }
    
}
