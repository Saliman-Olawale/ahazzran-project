package ci.orange.digital.center.ahazzran.webservice.repositories;


import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ci.orange.digital.center.ahazzran.webservice.entities.Chapitre;
import ci.orange.digital.center.ahazzran.webservice.entities.Progression;
import ci.orange.digital.center.ahazzran.webservice.entities.ProgressionChapitre;

@Repository

public interface ProgressionChapitreRepository extends JpaRepository<ProgressionChapitre, Integer> {
    List<ProgressionChapitre> findByEstTermine(boolean estTermine);

    List<ProgressionChapitre> findByProgressionProgressionId(int progressionId);

    List<ProgressionChapitre> findByProgressionProgressionIdAndFinChapitreBetweenAndEstTermineTrue(int id, Timestamp debutSemaine, Timestamp finSemaine);
    
    ProgressionChapitre findByProgressionAndChapitre(Progression progression, Chapitre chapitre);
}
