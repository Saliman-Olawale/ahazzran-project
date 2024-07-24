package ci.orange.digital.center.ahazzran.webservice.dtos;

public class InscriptionOutputDto {
    
     
    private String status;
    private String message;


    public InscriptionOutputDto() {
        super();
    }
 
    public InscriptionOutputDto(String status, String message) {
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
