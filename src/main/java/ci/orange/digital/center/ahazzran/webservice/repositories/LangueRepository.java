package ci.orange.digital.center.ahazzran.webservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ci.orange.digital.center.ahazzran.webservice.entities.Langue;
import java.util.List;


public interface LangueRepository extends JpaRepository<Langue, Integer>  {
    List<Langue> findByStatut(String statut);

}
