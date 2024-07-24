package ci.orange.digital.center.ahazzran.webservice.dtos;

public class ReinitializePasswordMobileOutputDto {
    

    private String status;
    private String message;


    public ReinitializePasswordMobileOutputDto() {
        super();
    }
 
    public ReinitializePasswordMobileOutputDto(String status, String message) {
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
