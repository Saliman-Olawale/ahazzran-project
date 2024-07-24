package ci.orange.digital.center.ahazzran.webservice.dtos;

public class CreateOrUpdateContenuLangueOutputDto {
    
    private String status;
    private String message;


    public CreateOrUpdateContenuLangueOutputDto() {
        super();
    }
 
    public CreateOrUpdateContenuLangueOutputDto(String status, String message) {
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