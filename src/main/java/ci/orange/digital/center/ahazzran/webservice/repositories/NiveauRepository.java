package ci.orange.digital.center.ahazzran.webservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ci.orange.digital.center.ahazzran.webservice.entities.Niveau;
import java.util.List;


public interface NiveauRepository extends JpaRepository<Niveau, Integer>{
    List<String> findByStatut(String statut);
    Niveau findByNom(String nomNiveau);
    Niveau findByPointMinLessThanEqualAndPointMaxGreaterThanEqual(int pointMin, int pointMax);
}
