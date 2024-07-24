package ci.orange.digital.center.ahazzran.webservice.dtos;

public class ConnexionOutputDto {
    private String status;
    private String message;
    private String token;


    public ConnexionOutputDto() {
        super();
    }
 
    public ConnexionOutputDto(String status, String message) {
        this.status = status;
        this.message = message;
    }
    
    public ConnexionOutputDto(String status, String message, String token) {
        this.status = status;
        this.message = message;
        this.token = token;
    }

    public String getStatus() {
        return status;
    }
    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }
    
    
}
