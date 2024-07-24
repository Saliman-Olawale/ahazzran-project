package ci.orange.digital.center.ahazzran.webservice.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ci.orange.digital.center.ahazzran.webservice.dtos.ChangerMotDePasseInputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.CheckEmailInputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.CheckEmailOutputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.ConnexionBackOfficeInputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.ConnexionInputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.ConnexionOutputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.EditerProfilInputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.InscriptionInputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.InscriptionOutputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.ReinitializePasswordMobileInputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.ReinitializePasswordMobileOutputDto;
import ci.orange.digital.center.ahazzran.webservice.services.IApprenantService;
import ci.orange.digital.center.ahazzran.webservice.services.IAuthenticationService;


@RestController
@RequestMapping("/auth")
public class AuthentificationController {

    private IAuthenticationService authenficationService;
    private IApprenantService apprenantService;

    public AuthentificationController(final IAuthenticationService authenficationService, final IApprenantService apprenantService) {
        this.authenficationService = authenficationService;
        this.apprenantService = apprenantService;
    }

    @PostMapping("/backoffice/checkemail")
    public ResponseEntity<ConnexionOutputDto> checkEmailBackOffice(@RequestBody ConnexionBackOfficeInputDto input) {
        ConnexionOutputDto result = authenficationService.checkEmailBackOffice(input);
        return ResponseEntity.ok(result);

    }

    @PostMapping("/backoffice/connexion")
    public ResponseEntity<ConnexionOutputDto> connexionBackOffice(@RequestBody ConnexionBackOfficeInputDto input) {
        ConnexionOutputDto result = authenficationService.connexionBackOffice(input);
        return ResponseEntity.ok(result);

    }

    @PostMapping("/mobile/connexion")
    public ResponseEntity<ConnexionOutputDto> connexionMobile(@RequestBody ConnexionInputDto input) {
        ConnexionOutputDto result = authenficationService.connexionMobile(input);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/mobile/checkemail")
    public ResponseEntity<CheckEmailOutputDto> checkEmailForOTP(@RequestBody CheckEmailInputDto input) {
        CheckEmailOutputDto resultat = authenficationService.checkEmail(input);
        return ResponseEntity.ok(resultat);

    }

    @PostMapping("/mobile/inscription")
    public ResponseEntity<InscriptionOutputDto> inscriptionMobile(@RequestBody InscriptionInputDto input) {
        InscriptionOutputDto resultat = authenficationService.inscriptionMobile(input);

        return ResponseEntity.ok(resultat);
    }

    @PostMapping("/mobile/motdepasseoublie")
    public ResponseEntity<ReinitializePasswordMobileOutputDto> motDePasseOublieMobile(
            @RequestBody ReinitializePasswordMobileInputDto input) {
        ReinitializePasswordMobileOutputDto resultat = authenficationService.motDePasseOublieMobile(input);

        return ResponseEntity.ok(resultat);
    }

    @PostMapping("/editerprofil")
    public ResponseEntity<ConnexionOutputDto> editer(@RequestBody EditerProfilInputDto input) {
        ConnexionOutputDto result = apprenantService.editProfil(input);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/changermotdepasse")
    public ResponseEntity<ConnexionOutputDto> changeMotDePasse(@RequestBody ChangerMotDePasseInputDto input) {
        ConnexionOutputDto result = apprenantService.changeMotDePasse(input);
        return ResponseEntity.ok(result);
    }
}
