package ci.orange.digital.center.ahazzran.webservice.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ci.orange.digital.center.ahazzran.webservice.dtos.ApprenantOuputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.ProfilDto;
import ci.orange.digital.center.ahazzran.webservice.services.IProgressionService;

@RestController
@RequestMapping("/api")

public class ProgressionController {

    private final IProgressionService progressionService;

    public ProgressionController(final IProgressionService progressionService ) {
        this.progressionService = progressionService;
    }

    @GetMapping("/progressions/xptotal") 
    public int getXpTotal() {
        int xpTotal = progressionService.calculerXpTotal();
 
        return xpTotal;    

    }

    @GetMapping("/progressions/listeapprenants/{langueId}")
    public List<ApprenantOuputDto> getApprenantsTriesParXpParLangue(@PathVariable int langueId) {
        return progressionService.getApprenantsTriesParXpParLangue(langueId);
    }

    @GetMapping("/progressions/rang-apprenant/{apprenantId}/{langueId}")
    public int getRangApprenantParLangue(@PathVariable int apprenantId,@PathVariable int langueId) {
        return progressionService.getRangApprenantParLangue(apprenantId, langueId);
    }

    @GetMapping("/progressions/top-30-apprenants/{langueId}")
    public List<ApprenantOuputDto> getTop30Apprenants(@PathVariable int langueId) {
        return progressionService.getTop30ApprenantsTriesParXpParLangue(langueId);
    }
    
    @GetMapping("/progressions/{apprenantId}/{langueId}")
    public ProfilDto getWeeklyProgression(@PathVariable int apprenantId,@PathVariable int langueId) {
        return progressionService.getWeeklyProgression(apprenantId, langueId);
    }
    
}
