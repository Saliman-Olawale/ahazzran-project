package ci.orange.digital.center.ahazzran.webservice.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ci.orange.digital.center.ahazzran.webservice.dtos.CreateOrUpdateNiveauInputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.NiveauOutputDto;
import ci.orange.digital.center.ahazzran.webservice.services.INiveauService;

@RestController
@RequestMapping("/api/niveau")

public class NiveauController {
    
    private INiveauService niveauService;


    public NiveauController(final INiveauService niveauService) {
        this.niveauService = niveauService;
    }


    @GetMapping("/getall")
    public ResponseEntity<List<NiveauOutputDto>> getAllNiveau() {
        return ResponseEntity.ok(niveauService.getAllNiveau());
    }

    @PostMapping("/createorupdate")
    public ResponseEntity<?> createOrUpdateNiveau(@RequestBody CreateOrUpdateNiveauInputDto input) {
        return ResponseEntity.ok(niveauService.CreateOrUpdateNiveau(input));
    }

    @PostMapping("/disable/{niveauId}")
    public ResponseEntity<?> disableNiveau(@PathVariable int niveauId) {
        return ResponseEntity.ok(niveauService.DisableNiveau(niveauId));
    }

    @PostMapping("/enable/{niveauId}")
    public ResponseEntity<?> enableNiveau(@PathVariable int niveauId) {
        return ResponseEntity.ok(niveauService.EnableNiveau(niveauId));
    }

    @PostMapping("/getNiveauByPoints")
    public ResponseEntity<String> getNiveauByPoints(@RequestBody int points) {
        return ResponseEntity.ok(niveauService.getNiveauByPoints(points));
    }
    
}
