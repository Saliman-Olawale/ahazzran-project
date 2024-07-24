package ci.orange.digital.center.ahazzran.webservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ci.orange.digital.center.ahazzran.webservice.entities.Evaluation;
import ci.orange.digital.center.ahazzran.webservice.entities.ICoursDetail;

public interface EvaluationRepository extends JpaRepository<Evaluation, Integer>{
    List<ICoursDetail> findAllByCoursId(int cours_id);

    List<ICoursDetail> findByCoursIdAndCoursDetailTemplateCoursDetailTemplateId(int coursId, int templateId);
}
