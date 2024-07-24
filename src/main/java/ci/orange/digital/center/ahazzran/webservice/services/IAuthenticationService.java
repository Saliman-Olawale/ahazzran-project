package ci.orange.digital.center.ahazzran.webservice.services;



import ci.orange.digital.center.ahazzran.webservice.dtos.CheckEmailInputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.CheckEmailOutputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.ConnexionBackOfficeInputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.ConnexionInputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.ConnexionOutputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.InscriptionInputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.InscriptionOutputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.ReinitializePasswordMobileInputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.ReinitializePasswordMobileOutputDto;

public interface IAuthenticationService {

    ConnexionOutputDto checkEmailBackOffice(ConnexionBackOfficeInputDto input);
    ConnexionOutputDto connexionBackOffice(ConnexionBackOfficeInputDto input);

    ConnexionOutputDto connexionMobile(ConnexionInputDto connexionInputDto);

    InscriptionOutputDto inscriptionMobile(InscriptionInputDto inscriptionInputDto);

    CheckEmailOutputDto checkEmail(CheckEmailInputDto input);

    ReinitializePasswordMobileOutputDto motDePasseOublieMobile(ReinitializePasswordMobileInputDto input);


}


