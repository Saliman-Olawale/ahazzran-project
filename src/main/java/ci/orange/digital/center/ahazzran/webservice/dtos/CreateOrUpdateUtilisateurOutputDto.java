package ci.orange.digital.center.ahazzran.webservice.dtos;

public class CreateOrUpdateUtilisateurOutputDto {
    
       
    private String status;
    private String message;


    public CreateOrUpdateUtilisateurOutputDto() {
        super();
    }
 
    public CreateOrUpdateUtilisateurOutputDto(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }
    public String getMessage() {
        return message;
    }
}
