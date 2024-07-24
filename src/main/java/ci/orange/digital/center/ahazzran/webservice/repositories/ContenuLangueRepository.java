package ci.orange.digital.center.ahazzran.webservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ci.orange.digital.center.ahazzran.webservice.entities.ContenuLangue;

@Repository
public interface ContenuLangueRepository extends JpaRepository<ContenuLangue, Integer>{

    List<ContenuLangue> findByLangueLangueId(int langueId); 

    List<ContenuLangue> findAllByLangueLangueId(int langueId); 

    List<ContenuLangue> findAllByContenuLangueIdIn(List<Integer> ids);  
    
}
