package ci.orange.digital.center.ahazzran.webservice.controllers;



import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ci.orange.digital.center.ahazzran.webservice.services.IApprenantService;

@RestController
@RequestMapping("/editerprofil")

public class ApprenantController {
    
    @SuppressWarnings("unused")
    private IApprenantService apprenantService;

    public ApprenantController(IApprenantService apprenantService) {
        this.apprenantService = apprenantService;
    }

   
}

