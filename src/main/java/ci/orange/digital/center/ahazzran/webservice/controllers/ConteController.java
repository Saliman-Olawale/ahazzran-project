package ci.orange.digital.center.ahazzran.webservice.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ci.orange.digital.center.ahazzran.webservice.dtos.ConteOutputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.CreateOrUpdateConteDto;
import ci.orange.digital.center.ahazzran.webservice.services.IConteService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("/api/contes")

public class ConteController {

    private IConteService service;

    public ConteController(final IConteService service) {
        this.service = service;
    }
    
    @GetMapping("/getenable/{langueId}")
    public ResponseEntity<List<ConteOutputDto>> getAllConteByLangueId(@PathVariable int langueId) {
        return ResponseEntity.ok(service.getAllConteByLangueId(langueId));
    }

    @GetMapping("/getall/{langueId}")
    public ResponseEntity<List<ConteOutputDto>> getAllConteByLangueIdBackOffice(@PathVariable int langueId) {
        return ResponseEntity.ok(service.getAllConteByLangueIdBackOffice(langueId));
    }

    @PostMapping(value = "/createorupdate", consumes = {"multipart/form-data"})
    public ResponseEntity<?> createOrUpdateConte(@RequestPart("input") String inputJson, 
                                                @RequestPart("file") MultipartFile file) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        CreateOrUpdateConteDto input = objectMapper.readValue(inputJson, CreateOrUpdateConteDto.class);
        return ResponseEntity.ok(service.CreateOrUpdateConte(input, file));
    }


    @PostMapping("/disable/{id}")
    public ResponseEntity<?> disableConte(@PathVariable int id) {
        return ResponseEntity.ok(service.DisableConte(id));
    }

    @PostMapping("/enable/{id}")
    public ResponseEntity<?> enableConte(@PathVariable int id) {
        return ResponseEntity.ok(service.EnableConte(id));
    }
}
