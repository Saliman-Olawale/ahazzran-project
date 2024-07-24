package ci.orange.digital.center.ahazzran.webservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ci.orange.digital.center.ahazzran.webservice.entities.Progression;

public interface ProgressionRepository extends JpaRepository<Progression, Integer>{

    List<Progression> findAllByLangueLangueIdOrderByXpAsc(int langueId);

    List<Progression> findAllByLangueLangueIdAndApprenantApprenantIdOrderByXpAsc(int langueId, int apprenantId);

    Progression findByApprenantApprenantIdAndLangueLangueId(int apprenantId, int langueId);

    int countDistinctDaysByApprenantApprenantId(int apprenantId);

    // List<Progression> findAllByOrderByXpAsc();

}