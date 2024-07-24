package ci.orange.digital.center.ahazzran.webservice.controllers;

import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ci.orange.digital.center.ahazzran.webservice.dtos.CreateOrUpdateDicoInputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.DictionnaireOutputDto;
import ci.orange.digital.center.ahazzran.webservice.services.IDictionnaireService;

@RestController
@RequestMapping("/api/dictionnaire")

public class DictionnaireController {
    
    private IDictionnaireService dictionnaireService;

    public DictionnaireController( final IDictionnaireService dictionnaireService){
        this.dictionnaireService = dictionnaireService;
    }

    
    @ModelAttribute
    
    @GetMapping("/getall")
    public ResponseEntity<List<DictionnaireOutputDto>> getAllDictionnaire() {
        return ResponseEntity.ok(dictionnaireService.getAllDictionnaire());
    }


    @PostMapping(value = "/createorupdate", consumes = {"multipart/form-data"})

    public ResponseEntity<?> createOrUpdateDictionnaire(@RequestPart("input") String inputJson,  
                                                        @RequestPart("file") MultipartFile file) throws JsonProcessingException  {

        ObjectMapper objectMapper = new ObjectMapper();
        CreateOrUpdateDicoInputDto input = objectMapper.readValue(inputJson, CreateOrUpdateDicoInputDto.class);
        return ResponseEntity.ok(dictionnaireService.CreateOrUpdateDictionnaire(input,file));
    }

    @PostMapping("disable/{dictionnaireId}")
    public ResponseEntity<?> disableDictionnaire(@PathVariable int dictionnaireId) {
        return ResponseEntity.ok(dictionnaireService.DisableDictionnaire(dictionnaireId));
    }
    @PostMapping("enable/{dictionnaireId}")
    public ResponseEntity<?> enableLangue(@PathVariable int dictionnaireId) {
        return ResponseEntity.ok(dictionnaireService.EnableDictionnaire(dictionnaireId));
    }
    
}
