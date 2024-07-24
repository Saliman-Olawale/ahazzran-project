package ci.orange.digital.center.ahazzran.webservice.services;

public interface IMailService {
    boolean SendMailForInscription(String email, String message);

    boolean SendMailForResetPincode(String email, String message);

    boolean  SendMailForConnexion(String email, String message);
}
