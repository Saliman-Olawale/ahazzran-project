package ci.orange.digital.center.ahazzran.webservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ci.orange.digital.center.ahazzran.webservice.entities.ExerciceChapitre;
import ci.orange.digital.center.ahazzran.webservice.entities.ICoursDetail;
import ci.orange.digital.center.ahazzran.webservice.entities.ProgressionChapitre;

@Repository
public interface ExerciceChapitreRepository extends JpaRepository<ExerciceChapitre, Integer> {
    List<ExerciceChapitre> findByChapitre_ProgressionChapitreIn(List<ProgressionChapitre> progressionChapitre);

    List<ICoursDetail> findAllByChapitreChapitreId(int chapitreId);
    List<ICoursDetail> findByChapitreChapitreIdAndCoursDetailTemplateCoursDetailTemplateId(int chapitreId, int templateId);
}
