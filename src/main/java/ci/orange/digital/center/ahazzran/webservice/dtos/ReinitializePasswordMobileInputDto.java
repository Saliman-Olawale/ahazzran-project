package ci.orange.digital.center.ahazzran.webservice.dtos;

public class ReinitializePasswordMobileInputDto {

    private String email;
    private String password;
    private String confirmPassword;
    private String otp;



    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getOtp() {
        return otp;
    }

   
    
}
