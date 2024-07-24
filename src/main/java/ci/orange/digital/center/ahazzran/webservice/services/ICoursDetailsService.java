package ci.orange.digital.center.ahazzran.webservice.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import ci.orange.digital.center.ahazzran.webservice.dtos.CoursDetailDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.CoursDetailTemplateDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.DefinirCoursInputOuputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.NiveauInfo;
import ci.orange.digital.center.ahazzran.webservice.dtos.TemplateReponseDto;



public interface ICoursDetailsService {

    List<CoursDetailDto> getCoursDetail(int id_cours, int langueId, String type);

    List<CoursDetailTemplateDto> getAllCoursDetailTemplates();

    boolean defineCours(DefinirCoursInputOuputDto input, String type);

    NiveauInfo generateRecompense(String typeContent,int coursId,List<TemplateReponseDto> input,List<MultipartFile> files) throws Exception;

}
