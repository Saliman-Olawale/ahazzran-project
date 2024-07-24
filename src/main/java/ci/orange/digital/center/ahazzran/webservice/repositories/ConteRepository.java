package ci.orange.digital.center.ahazzran.webservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ci.orange.digital.center.ahazzran.webservice.entities.Conte;

@Repository
public interface ConteRepository extends JpaRepository<Conte, Integer>{

    List<Conte> findByStatutAndLangueLangueId(String statut, int langueId);

    List<Conte> findAllByLangueLangueId(int langueId);
   
   
}
