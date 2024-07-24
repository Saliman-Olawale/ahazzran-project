package ci.orange.digital.center.ahazzran.webservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ci.orange.digital.center.ahazzran.webservice.entities.Cours;

public interface CoursRepository extends JpaRepository<Cours, Integer>{

    List<Cours> findByChapitreChapitreId(int chapitreId);    
    
}
