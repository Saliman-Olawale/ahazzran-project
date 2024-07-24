package ci.orange.digital.center.ahazzran.webservice.services;

import java.util.List;

import org.springframework.stereotype.Service;

import ci.orange.digital.center.ahazzran.webservice.entities.ICoursDetail;
import ci.orange.digital.center.ahazzran.webservice.repositories.CoursDetailRepository;
import ci.orange.digital.center.ahazzran.webservice.repositories.ExerciceChapitreRepository;
import ci.orange.digital.center.ahazzran.webservice.repositories.EvaluationRepository;


@Service
public class CoursDetailFactoryService {

    private CoursDetailRepository coursDetailRepository;
    private ExerciceChapitreRepository exerciceChapitreRepository;
    private EvaluationRepository evaluationRepository;

    public CoursDetailFactoryService(final CoursDetailRepository coursDetailRepository, final ExerciceChapitreRepository exerciceChapitreRepository, final EvaluationRepository evaluationRepository) {
        this.coursDetailRepository = coursDetailRepository;
        this.exerciceChapitreRepository = exerciceChapitreRepository;
        this.evaluationRepository = evaluationRepository;
    }


    public List<ICoursDetail> getCoursDetailById(int id, String type) {

        switch (type.toLowerCase()) {
            
            case "cours":

                if (coursDetailRepository.findAllByCoursCoursId(id) != null) {
                    return coursDetailRepository.findAllByCoursCoursId(id);
                } else {
                    throw new RuntimeException("CoursDetail not found for id " + id);
                }

            case "exercice":
            
                if (exerciceChapitreRepository.findAllByChapitreChapitreId(id) != null) {
                    return exerciceChapitreRepository.findAllByChapitreChapitreId(id);
                } else {
                    throw new RuntimeException("Exercice de Chapitre introuvable " + id);
                }
            
            case "evaluation":
            
                if (evaluationRepository.findAllByCoursId(id) != null) {
                    return evaluationRepository.findAllByCoursId(id);
                } else {
                    throw new RuntimeException("Evaluation introuvable " + id);
                }            
            
            default:
                throw new IllegalArgumentException("Unknown type: " + type);
        }
    }


    public List<ICoursDetail> getInfoAboutTemplateIdAndContentTypeId(int contentTypeId,int templateId, String type) {

        switch (type.toLowerCase()) {
            
            case "cours":

            List<ICoursDetail> coursDetails = coursDetailRepository.findByCoursCoursIdAndCoursDetailTemplateCoursDetailTemplateId(contentTypeId, templateId);

                if (coursDetails!= null) {
                    return coursDetails;
                } else {
                    throw new RuntimeException("CoursDetail not found for id " + contentTypeId);
                }

            case "evaluation":

                List<ICoursDetail> evaluationDetails = evaluationRepository.findByCoursIdAndCoursDetailTemplateCoursDetailTemplateId(contentTypeId, templateId);
                if (evaluationDetails != null) {
                    return evaluationDetails;
                } else {
                    throw new RuntimeException("Exercice de Chapitre introuvable " + contentTypeId);
                }
            
            case "exercice":

                List<ICoursDetail> exerciceDetails = exerciceChapitreRepository.findByChapitreChapitreIdAndCoursDetailTemplateCoursDetailTemplateId(contentTypeId, templateId);
            
                if (exerciceDetails != null) {
                    return exerciceDetails;
                } else {
                    throw new RuntimeException("Evaluation introuvable " + contentTypeId);
                }            
            
            default:
                throw new IllegalArgumentException("Unknown type: " + type);
        }
    }


}
