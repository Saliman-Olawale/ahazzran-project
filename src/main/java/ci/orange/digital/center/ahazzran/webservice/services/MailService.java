package ci.orange.digital.center.ahazzran.webservice.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService implements IMailService {

    private JavaMailSender javaMailSender;

    public MailService(final JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public boolean SendMailForInscription(String email, String message) {
        String html = "<!DOCTYPE html>" +
                "<html lang=\"fr\">" +
                "<head>" +
                "<meta charset=\"UTF-8\">" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                "<title>Inscription AHAZZRAN</title>" +
                "<style>" +
                "body {" +
                "    font-family: Arial, sans-serif;" +
                "    line-height: 1.6;" +
                "    color: #333;" +
                "    background-color: #f9f9f9;" +
                "    padding: 20px;" +
                "    text-align: center;" +
                "}" +
                ".container {" +
                "    background-color: #fff;" +
                "    border-radius: 10px;" +
                "    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);" +
                "    padding: 20px;" +
                "    max-width: 600px;" +
                "    margin: auto;" +
                "}" +
                "h1 {" +
                "    color: #4CAF50;" +
                "}" +
                ".otp {" +
                "    border: 2px solid #4CAF50;" +
                "    padding: 10px;" +
                "    display: inline-block;" +
                "    font-size: 1.5em;" +
                "    margin: 20px 0;" +
                "}" +
                "p {" +
                "    margin: 10px 0;" +
                "}" +
                ".footer {" +
                "    margin-top: 20px;" +
                "    font-size: 0.9em;" +
                "    color: #777;" +
                "}" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<div class=\"container\">" +
                "<h1>Bienvenue chez AHAZZRAN</h1>" +
                "<h3>Votre code OTP (One-Time Password) est :</h3>" +
                "<div class=\"otp\">" +
                "<strong>" + message + "</strong>" +
                "</div>" +
                "<p>Merci de vouloir vous inscrire sur notre plateforme. Votre code OTP est requis pour compléter votre inscription et vérifier votre adresse e-mail.</p>" +
                "<p>Veuillez entrer ce code dans l'application pour finaliser le processus d'inscription.</p>" +
                "<p>Assurez-vous de ne pas partager ce code avec quiconque pour des raisons de sécurité.</p>" +
                "<p>Si vous n'avez pas demandé ce code, veuillez ignorer cet e-mail.</p>" +
                "<p>Nous sommes ravis de vous accueillir et espérons que vous apprécierez votre expérience avec AHAZZRAN.</p>" +
                "<p>Cordialement,</p>" +
                "<p><strong>L'équipe de support AHAZZRAN</strong></p>" +
                "<div class=\"footer\">" +
                "<p>Si vous avez des questions, n'hésitez pas à nous contacter à support@ahazzran.com.</p>" +
                "<p>&copy; 2024 AHAZZRAN. Tous droits réservés.</p>" +
                "</div>" +
                "</div>" +
                "</body>" +
                "</html>";

        return SendMail(email, "AHAZZRAN Inscription", html);
    }


    @Override
    public boolean SendMailForResetPincode(String email, String message) {
        String html = "<!DOCTYPE html>" +
                "<html lang=\"fr\">" +
                "<head>" +
                "<meta charset=\"UTF-8\">" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                "<title>Réinitialisation de votre mot de passe - AHAZZRAN</title>" +
                "<style>" +
                "body {" +
                "    font-family: Arial, sans-serif;" +
                "    line-height: 1.6;" +
                "    color: #333;" +
                "    background-color: #f9f9f9;" +
                "    padding: 20px;" +
                "    text-align: center;" +
                "}" +
                ".container {" +
                "    background-color: #fff;" +
                "    border-radius: 10px;" +
                "    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);" +
                "    padding: 20px;" +
                "    max-width: 600px;" +
                "    margin: auto;" +
                "}" +
                "h1 {" +
                "    color: #4CAF50;" +
                "}" +
                ".otp {" +
                "    border: 2px solid #4CAF50;" +
                "    padding: 10px;" +
                "    display: inline-block;" +
                "    font-size: 1.5em;" +
                "    margin: 20px 0;" +
                "}" +
                "p {" +
                "    margin: 10px 0;" +
                "}" +
                ".footer {" +
                "    margin-top: 20px;" +
                "    font-size: 0.9em;" +
                "    color: #777;" +
                "}" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<div class=\"container\">" +
                "<h1>Réinitialisation de votre mot de passe - AHAZZRAN</h1>" +
                "<p>Nous avons reçu une demande de réinitialisation de votre mot de passe.</p>" +
                "<h3>Votre code OTP est :</h3>" +
                "<div class=\"otp\">" +
                "<strong>" + message + "</strong>" +
                "</div>" +
                "<p>Veuillez utiliser ce code pour réinitialiser votre mot de passe dans l'application.</p>" +
                "<p>Pour des raisons de sécurité, merci de ne pas partager ce code avec quiconque.</p>" +
                "<p>Si vous n'avez pas demandé cette réinitialisation, veuillez ignorer cet e-mail.</p>" +
                "<p>Merci,</p>" +
                "<p><strong>L'équipe de support AHAZZRAN</strong></p>" +
                "<div class=\"footer\">" +
                "<p>Si vous avez des questions, n'hésitez pas à nous contacter à support@ahazzran.com.</p>" +
                "<p>&copy; 2024 AHAZZRAN. Tous droits réservés.</p>" +
                "</div>" +
                "</div>" +
                "</body>" +
                "</html>";
    
        return SendMail(email, "AHAZZRAN Réinitialisation de votre mot de passe", html);
    }
    

    @Override
    public boolean SendMailForConnexion(String email, String message) {
        String html = "<!DOCTYPE html>" +
                "<html lang=\"fr\">" +
                "<head>" +
                "<meta charset=\"UTF-8\">" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                "<title>Connexion - AHAZZRAN</title>" +
                "<style>" +
                "body {" +
                "    font-family: Arial, sans-serif;" +
                "    line-height: 1.6;" +
                "    color: #333;" +
                "    background-color: #f9f9f9;" +
                "    padding: 20px;" +
                "    text-align: center;" +
                "}" +
                ".container {" +
                "    background-color: #fff;" +
                "    border-radius: 10px;" +
                "    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);" +
                "    padding: 20px;" +
                "    max-width: 600px;" +
                "    margin: auto;" +
                "}" +
                "h1 {" +
                "    color: #4CAF50;" +
                "}" +
                ".otp {" +
                "    border: 2px solid #4CAF50;" +
                "    padding: 10px;" +
                "    display: inline-block;" +
                "    font-size: 1.5em;" +
                "    margin: 20px 0;" +
                "}" +
                "p {" +
                "    margin: 10px 0;" +
                "}" +
                ".footer {" +
                "    margin-top: 20px;" +
                "    font-size: 0.9em;" +
                "    color: #777;" +
                "}" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<div class=\"container\">" +
                "<h1>Connexion - AHAZZRAN</h1>" +
                "<p>Nous avons reçu une demande de connexion à votre compte.</p>" +
                "<h3>Votre code OTP est :</h3>" +
                "<div class=\"otp\">" +
                "<strong>" + message + "</strong>" +
                "</div>" +
                "<p>Veuillez utiliser ce code pour vous connecter à votre compte dans l'application.</p>" +
                "<p>Pour des raisons de sécurité, merci de ne pas partager ce code avec quiconque.</p>" +
                "<p>Merci,</p>" +
                "<p><strong>L'équipe de support AHAZZRAN</strong></p>" +
                "<div class=\"footer\">" +
                "<p>Si vous avez des questions, n'hésitez pas à nous contacter à support@ahazzran.com.</p>" +
                "<p>&copy; 2024 AHAZZRAN. Tous droits réservés.</p>" +
                "</div>" +
                "</div>" +
                "</body>" +
                "</html>";

        return SendMail(email, "AHAZZRAN Connexion", html);
    }


    private boolean SendMail(String email, String subject, String message) {

        try {

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper minMimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            minMimeMessageHelper.setTo(email);
            minMimeMessageHelper.setSubject(subject);
            minMimeMessageHelper.setText(message, true);

            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            return false;
        }
        return true;

    }

}
