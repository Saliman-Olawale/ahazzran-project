package ci.orange.digital.center.ahazzran.webservice.services;


import org.springframework.stereotype.Service;

import ci.orange.digital.center.ahazzran.webservice.dtos.CoursDetailDto;
import ci.orange.digital.center.ahazzran.webservice.entities.ContenuLangue;
import ci.orange.digital.center.ahazzran.webservice.entities.ICoursDetail;
import ci.orange.digital.center.ahazzran.webservice.repositories.ContenuLangueRepository;

@Service
public class ComparaisonVocale implements IComparaisonVocale {

    private ContenuLangueRepository contenuLangueRepository;

    public ComparaisonVocale(ContenuLangueRepository contenuLangueRepository) {
        this.contenuLangueRepository = contenuLangueRepository;
    }

    @Override
    public CoursDetailDto generateCoursDetail(ICoursDetail coursDetail,int contenuLangueId) {

        ContenuLangue correctContenuLangue = contenuLangueRepository.findById(contenuLangueId).orElseThrow(() -> new IllegalArgumentException("ContenuLangue Introuvable pour ComparaisonVocale"));

        CoursDetailDto dto = new CoursDetailDto(coursDetail, correctContenuLangue);
    
        return dto;
    }
    
}
