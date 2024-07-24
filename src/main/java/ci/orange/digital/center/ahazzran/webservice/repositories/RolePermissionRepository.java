package ci.orange.digital.center.ahazzran.webservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ci.orange.digital.center.ahazzran.webservice.entities.RolePermission;

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermission, Integer> {

    List<RolePermission>  findByRoleRoleId(int roleId);
    
}
