package ci.orange.digital.center.ahazzran.webservice.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ci.orange.digital.center.ahazzran.webservice.dtos.CreateOrUpdateContenuLangueInputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.CreateOrUpdateContenuLangueOutputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.LangueGroupDto;
import ci.orange.digital.center.ahazzran.webservice.services.IContenuLangueService;

@RestController
@RequestMapping("/api/contenulangue")

public class ContenuLangueController {

    private IContenuLangueService contenuLangueService;

    public ContenuLangueController(final IContenuLangueService contenuLangueService) {
        this.contenuLangueService = contenuLangueService;
    }
  
    @GetMapping("/getone/{langueId}")
    public ResponseEntity<LangueGroupDto> getContenusByLangueId(@PathVariable int langueId) {
        LangueGroupDto contenus = contenuLangueService.getContenusByLangueId(langueId);
            return ResponseEntity.ok(contenus);
    }


    @PostMapping(value = "/createorupdate", consumes = {"multipart/form-data"})
    public ResponseEntity<CreateOrUpdateContenuLangueOutputDto> createOrUpdateContenuLangue(@RequestPart("input") String inputJson, @RequestPart("file") MultipartFile file) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        CreateOrUpdateContenuLangueInputDto input = objectMapper.readValue(inputJson, CreateOrUpdateContenuLangueInputDto.class);

        CreateOrUpdateContenuLangueOutputDto resultat = contenuLangueService.CreateOrUpdateContenuLangue(input, file);
        return ResponseEntity.ok(resultat);
    }

    
    @PostMapping("disable/{contenuLangueId}")
    public ResponseEntity<?> disableContenuLangue(@PathVariable int contenuLangueId) {
        return ResponseEntity.ok(contenuLangueService.DisableContenuLangue(contenuLangueId));
    }

    @PostMapping("enable/{contenuLangueId}")
    public ResponseEntity<?> enableContenuLangue(@PathVariable int contenuLangueId) {
        return ResponseEntity.ok(contenuLangueService.EnableContenuLangue(contenuLangueId));
    }
}


