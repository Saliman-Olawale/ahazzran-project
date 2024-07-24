package ci.orange.digital.center.ahazzran.webservice.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ci.orange.digital.center.ahazzran.webservice.dtos.ChapitreCoursDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.ChapitreOutputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.CreateOrUpdateChapitreInputDto;
import ci.orange.digital.center.ahazzran.webservice.services.IChapitreService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/chapitres")

public class ChapitreController {
    private IChapitreService chapitreService; 

    public ChapitreController(final IChapitreService chapitreService) {
        this.chapitreService = chapitreService;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<ChapitreOutputDto>> getAllChapitres() {
        return ResponseEntity.ok(chapitreService.getAllChapitres());
    }

    @PostMapping("/createorupdate")
    public ResponseEntity<?> createOrUpdateChapitre(@RequestBody CreateOrUpdateChapitreInputDto input) {
        return ResponseEntity.ok(chapitreService.CreateOrUpdateChapitre(input));
    }
    
    @PostMapping("/disable/{input}")
    public ResponseEntity<?> disableChapitre(@PathVariable int input) {
        return ResponseEntity.ok(chapitreService.DisableChapitre(input));
    }
    @PostMapping("/enable/{input}")
    public ResponseEntity<?> enableChapitre(@PathVariable int input) {
        return ResponseEntity.ok(chapitreService.EnableChapitre(input));
    }

     @GetMapping("/cours")
    public List<ChapitreCoursDto> getChapitresWithCours() {
        return chapitreService.getChapitresWithCours();
    }
}
