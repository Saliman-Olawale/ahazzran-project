package ci.orange.digital.center.ahazzran.webservice.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ci.orange.digital.center.ahazzran.webservice.entities.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer> {

    
}
