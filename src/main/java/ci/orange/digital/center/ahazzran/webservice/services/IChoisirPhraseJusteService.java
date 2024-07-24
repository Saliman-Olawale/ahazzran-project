package ci.orange.digital.center.ahazzran.webservice.services;

import java.util.List;

import ci.orange.digital.center.ahazzran.webservice.dtos.CoursDetailDto;
import ci.orange.digital.center.ahazzran.webservice.entities.ContenuLangue;
import ci.orange.digital.center.ahazzran.webservice.entities.ICoursDetail;

public interface IChoisirPhraseJusteService {

    CoursDetailDto generateCoursDetail(ICoursDetail coursDetail, List<ContenuLangue> contenuLangues, int contenuLangueId);

}
