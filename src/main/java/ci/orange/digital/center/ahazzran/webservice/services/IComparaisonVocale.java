package ci.orange.digital.center.ahazzran.webservice.services;


import ci.orange.digital.center.ahazzran.webservice.dtos.CoursDetailDto;
import ci.orange.digital.center.ahazzran.webservice.entities.ICoursDetail;

public interface IComparaisonVocale {

    CoursDetailDto generateCoursDetail(ICoursDetail coursDetail, int contenuLangueId);
    
}
