package ci.orange.digital.center.ahazzran.webservice.controllers;

import ci.orange.digital.center.ahazzran.webservice.dtos.CreateOrUpdateLangueInputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.LangueMobileOutputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.LangueOutputDto;
import ci.orange.digital.center.ahazzran.webservice.services.ILangueService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/langues")
public class LangueController {

    private ILangueService langueService;

    public LangueController(final ILangueService langueService) {
        this.langueService = langueService;
    }


    @GetMapping("/getall")
    public ResponseEntity<List<LangueOutputDto>> getAllLangues() {
        return ResponseEntity.ok(langueService.getAllLangues());
    }
    
    @GetMapping("mobile/getallLangues")
    public ResponseEntity<List<LangueMobileOutputDto>> getAllLanguesForMobile() {
        return ResponseEntity.ok(langueService.getAllLanguesEnable());
    }

    @PostMapping("/createorupdate")
    public ResponseEntity<?> createOrUpdateLangue(@RequestBody CreateOrUpdateLangueInputDto input) {
        return ResponseEntity.ok(langueService.CreateOrUpdateLangue(input));
    }

    @PostMapping("disable/{langueId}")
    public ResponseEntity<?> disableLangue(@PathVariable int langueId) {
        return ResponseEntity.ok(langueService.DisableLangue(langueId));
    }
    @PostMapping("enable/{langueId}")
    public ResponseEntity<?> enableLangue(@PathVariable int langueId) {
        return ResponseEntity.ok(langueService.EnableLangue(langueId));
    }

}
