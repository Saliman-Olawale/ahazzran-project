package ci.orange.digital.center.ahazzran.webservice.services;

import java.util.List;

import ci.orange.digital.center.ahazzran.webservice.dtos.CoursDetailDto;
import ci.orange.digital.center.ahazzran.webservice.entities.ContenuLangue;
import ci.orange.digital.center.ahazzran.webservice.entities.ICoursDetail;

public interface IMotsEtImageService {

    CoursDetailDto generateCoursMotsEtImages(List<ContenuLangue> contenuLangues, ICoursDetail coursDetail, List<Integer> contenuLangueIds);

}