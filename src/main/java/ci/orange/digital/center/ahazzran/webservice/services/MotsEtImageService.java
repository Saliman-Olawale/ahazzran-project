package ci.orange.digital.center.ahazzran.webservice.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import ci.orange.digital.center.ahazzran.webservice.dtos.CoursDetailDto;
import ci.orange.digital.center.ahazzran.webservice.entities.ContenuLangue;
import ci.orange.digital.center.ahazzran.webservice.entities.ICoursDetail;

@Service
public class MotsEtImageService implements IMotsEtImageService {

   

    @Override
    public CoursDetailDto generateCoursMotsEtImages(List<ContenuLangue> contenuLangues, ICoursDetail coursDetail, List<Integer> contenuLangueIds) {


        List<CoursDetailDto.ImagesDto> imagesDtoList = new ArrayList<>();
        List<CoursDetailDto.MotsDto> motsDtoList = new ArrayList<>();


        for (int i = 0; i < contenuLangues.size() ; i++) {
            
            
            CoursDetailDto.ImagesDto imageDto = new CoursDetailDto.ImagesDto(contenuLangues.get(i));
            imagesDtoList.add(imageDto);

            CoursDetailDto.MotsDto motDto = new CoursDetailDto.MotsDto(contenuLangues.get(i));
            motsDtoList.add(motDto);
        }

        CoursDetailDto dto = new CoursDetailDto(motsDtoList,imagesDtoList,coursDetail);

        return dto; 
    }

}


/*
    ContenuLangue correctContenuLangue = contenuLangues.stream()
            .filter(cl -> cl.getContenuLangueId() == contenuLangueId)
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Contenu de dictionnaire correct non trouvé."));

 *  List<Integer> coursDetailIds = coursDetails.stream()
        .map(ICoursDetail::getId)  // Supposons que la méthode pour obtenir l'ID s'appelle getId()
        .collect(Collectors.toList());

        System.out.println(coursDetailIds);
 */