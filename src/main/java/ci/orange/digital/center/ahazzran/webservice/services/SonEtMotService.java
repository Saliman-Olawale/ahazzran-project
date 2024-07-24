package ci.orange.digital.center.ahazzran.webservice.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import ci.orange.digital.center.ahazzran.webservice.dtos.CoursDetailDto;
import ci.orange.digital.center.ahazzran.webservice.entities.ContenuLangue;
import ci.orange.digital.center.ahazzran.webservice.entities.ICoursDetail;

@Service
public class SonEtMotService implements ISonEtMotService{


    @Override
    public CoursDetailDto generateTemplatesSonEtMots(List<ContenuLangue> contenuLangues,ICoursDetail coursDetail, List<Integer> contenuLangueIds) {
                    

        List<CoursDetailDto.AudioDto> audiosDtoList = new ArrayList<>();
        List<CoursDetailDto.MotsDto> motsDtoList = new ArrayList<>();


        for (int i = 0; i < contenuLangues.size() ; i++) {
            
            CoursDetailDto.AudioDto audioDto = new CoursDetailDto.AudioDto(contenuLangues.get(i));
            audiosDtoList.add(audioDto);

            CoursDetailDto.MotsDto motDto = new CoursDetailDto.MotsDto(contenuLangues.get(i));
            motsDtoList.add(motDto);
        }
        
        
        CoursDetailDto dto = new CoursDetailDto(coursDetail, audiosDtoList, motsDtoList);

        return dto; 
    }
    
}
