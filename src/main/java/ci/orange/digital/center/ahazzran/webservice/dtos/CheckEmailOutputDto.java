package ci.orange.digital.center.ahazzran.webservice.dtos;

public class CheckEmailOutputDto {
    
    private String status;
    private String message;


    public CheckEmailOutputDto() {
        super();
    }
 
    public CheckEmailOutputDto(String status, String message) {
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
