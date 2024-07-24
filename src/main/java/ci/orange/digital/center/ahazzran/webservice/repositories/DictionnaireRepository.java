package ci.orange.digital.center.ahazzran.webservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ci.orange.digital.center.ahazzran.webservice.entities.Dictionnaire;


public interface DictionnaireRepository extends JpaRepository<Dictionnaire, Integer> {
        List<Dictionnaire> findByStatut(String statut);

}
