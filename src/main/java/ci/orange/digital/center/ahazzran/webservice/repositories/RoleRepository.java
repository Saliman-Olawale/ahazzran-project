package ci.orange.digital.center.ahazzran.webservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ci.orange.digital.center.ahazzran.webservice.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>  {
    
}
