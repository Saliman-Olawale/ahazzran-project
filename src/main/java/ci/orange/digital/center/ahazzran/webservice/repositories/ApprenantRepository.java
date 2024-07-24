package ci.orange.digital.center.ahazzran.webservice.repositories;

import ci.orange.digital.center.ahazzran.webservice.entities.Apprenant;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApprenantRepository extends JpaRepository<Apprenant, Integer> {
    Apprenant findByEmail(String email);
}

