package ci.orange.digital.center.ahazzran.webservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ci.orange.digital.center.ahazzran.webservice.entities.CoursDetail;
import ci.orange.digital.center.ahazzran.webservice.entities.ICoursDetail;
import ci.orange.digital.center.ahazzran.webservice.entities.ProgressionCours;

@Repository
public interface CoursDetailRepository extends JpaRepository<CoursDetail, Integer> {

    List<CoursDetail> findByCours_ProgressionCoursIn(List<ProgressionCours> progressionCours);

    List<ICoursDetail> findAllByCoursCoursId(int coursId);

    CoursDetail findByCoursCoursId(int coursId);

    List<ICoursDetail> findByCoursCoursIdAndCoursDetailTemplateCoursDetailTemplateId(int coursId, int templateId);

}