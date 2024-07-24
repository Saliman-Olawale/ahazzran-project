package ci.orange.digital.center.ahazzran.webservice.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import ci.orange.digital.center.ahazzran.webservice.dtos.ChapitreGroupOutputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.CoursDetailDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.CoursDetailTemplateDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.CreateOrUpdateCoursInputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.CreateOrUpdateCoursOutputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.DefinirCoursInputOuputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.NiveauInfo;
import ci.orange.digital.center.ahazzran.webservice.dtos.ReponsesAuxTemplateInputDto;
import ci.orange.digital.center.ahazzran.webservice.services.ICoursDetailsService;
import ci.orange.digital.center.ahazzran.webservice.services.ICoursService;


@RestController
@RequestMapping("/api/cours")

public class CoursController {
    
    private ICoursService coursService;
    private ICoursDetailsService coursDetailService;
    private static final Logger logger = LoggerFactory.getLogger(CoursController.class);


    public CoursController(final ICoursService coursService, final ICoursDetailsService coursDetailService) {
        this.coursService = coursService;
        this.coursDetailService = coursDetailService;
    }

    @GetMapping("/getone/{chapitreId}")
    public ResponseEntity<ChapitreGroupOutputDto> getCoursByChapitreId(@PathVariable int chapitreId) {
        ChapitreGroupOutputDto contenus = coursService.getCoursByChapitreId(chapitreId);
            return ResponseEntity.ok(contenus);
    }

    @PostMapping("/createorupdate")
    public ResponseEntity<CreateOrUpdateCoursOutputDto> createOrUpdateCours(@RequestBody CreateOrUpdateCoursInputDto input) {
        CreateOrUpdateCoursOutputDto resultat = coursService.CreateOrUpdateCours(input);
        return ResponseEntity.ok(resultat);
    }
    
    @PostMapping("/disable/{coursId}")
    public ResponseEntity<?> disableCours(@PathVariable int coursId) {
        boolean resultat = coursService.DisableCours(coursId);
        return ResponseEntity.ok(resultat);
    }

    @PostMapping("/enable/{coursId}")
    public ResponseEntity<?> enableCours(@PathVariable int coursId) {
        boolean resultat = coursService.EnableCours(coursId);
        return ResponseEntity.ok(resultat);
    }

    @GetMapping("/getcours/{langueId}/{id}/{type}")
    public ResponseEntity<List<CoursDetailDto>> getCoursDetail(@PathVariable int langueId, @PathVariable int id, @PathVariable String type) {

        return ResponseEntity.ok(coursDetailService.getCoursDetail(id,langueId, type));
    }

    @GetMapping("/getAlltemplates")
    public List<CoursDetailTemplateDto> getAllCoursDetailTemplates() {
        return coursDetailService.getAllCoursDetailTemplates();
    }

    @PostMapping("/definetemplatesdecours")
    public ResponseEntity<Boolean> defineCours(@RequestBody DefinirCoursInputOuputDto input, @RequestBody String type) {
        return ResponseEntity.ok(coursDetailService.defineCours(input,type));
    }

  
    @PostMapping(value = "/getNiveauByPointsOrRecompenses/{typeContent}", consumes = {"multipart/form-data"})
    public ResponseEntity<NiveauInfo> getNiveau(@PathVariable String typeContent, @RequestPart("data") String inputJson, 
                                                @RequestPart(value = "file", required = false) List<MultipartFile> files) throws Exception {
                                                                         
        ReponsesAuxTemplateInputDto input = new ObjectMapper().readValue(inputJson, ReponsesAuxTemplateInputDto.class);
            
        logger.info("Request received with coursId: {}", input.getCoursId());
        return ResponseEntity.ok(coursDetailService.generateRecompense(typeContent,input.getCoursId(),input.getData(), files));
    }

}
