package ci.orange.digital.center.ahazzran.webservice.dtos;

public class CreateOrUpdateCoursOutputDto {
    
    private String status;
    private String message;


    public CreateOrUpdateCoursOutputDto() {
        super();
    }
 
    public CreateOrUpdateCoursOutputDto(String status, String message) {
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
