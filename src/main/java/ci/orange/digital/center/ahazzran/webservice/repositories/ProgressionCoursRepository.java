package ci.orange.digital.center.ahazzran.webservice.repositories;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ci.orange.digital.center.ahazzran.webservice.entities.Cours;
import ci.orange.digital.center.ahazzran.webservice.entities.ProgressionChapitre;
import ci.orange.digital.center.ahazzran.webservice.entities.ProgressionCours;

@Repository

public interface ProgressionCoursRepository extends JpaRepository<ProgressionCours, Integer> {

    List<ProgressionCours> findByEstTermine(boolean estTermine);
    ProgressionCours findByCoursCoursId(int coursId);

    List<ProgressionCours> findByProgressionChapitreAndFinCoursBetweenAndEstTermineTrue(ProgressionChapitre progressionChapitre,Timestamp debutSemaine, Timestamp finSemaine);
    ProgressionCours findByProgressionChapitreAndCours(ProgressionChapitre progressionChapitre,Cours  cours);
    List<ProgressionCours> findByProgressionChapitreProgressionChapitreId(int progressionChapitreId);

}

