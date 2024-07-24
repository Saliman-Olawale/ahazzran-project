package ci.orange.digital.center.ahazzran.webservice.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ci.orange.digital.center.ahazzran.webservice.dtos.CreateOrUpdateUtilisateurInputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.CreateOrUpdateUtilisateurOutputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.UtilisateurOutputDto;
import ci.orange.digital.center.ahazzran.webservice.services.IUtilisateurService;
    

@RestController
@RequestMapping("/api/utilisateur")
public class UtilisateurController {

    private IUtilisateurService utilisateurService;

    public UtilisateurController(final IUtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }


    @GetMapping("/getall")
    public ResponseEntity<List<UtilisateurOutputDto>> getAllUtilisateurs() {
        return ResponseEntity.ok(utilisateurService.getAllUtilisateurs());
    }

    @PostMapping("/createorupdate")
    public ResponseEntity<CreateOrUpdateUtilisateurOutputDto> createOrUpdateUtilisateur(@RequestBody CreateOrUpdateUtilisateurInputDto input) {
        CreateOrUpdateUtilisateurOutputDto resultat = utilisateurService.createOrUpdateUtilisateur(input);
        return ResponseEntity.ok(resultat);
    }

    @PostMapping("disable/{utilisateurId}")
    public ResponseEntity<?> disableUtilisateur(@PathVariable int utilisateurId) {
        return ResponseEntity.ok(utilisateurService.DisableUtilisateur(utilisateurId));
    }

    @PostMapping("enable/{utilisateurId}")
    public ResponseEntity<?> enableUtilisateur(@PathVariable int utilisateurId) {
        return ResponseEntity.ok(utilisateurService.EnableUtilisateur(utilisateurId));
    }

}


