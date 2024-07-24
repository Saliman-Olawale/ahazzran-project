package ci.orange.digital.center.ahazzran.webservice.dtos;

public class InscriptionInputDto {

    private String email;
    private String password;
    private String confirmPassword;
    private int langueId;
    private int niveauId;
    private String otp;


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public int getLangueId() {
        return langueId;
    }

    public int getNiveauId() {
        return niveauId;
    }

    public String getOtp() {
        return otp;
    }


    
}
