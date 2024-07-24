package ci.orange.digital.center.ahazzran.webservice.dtos;

public class CreateOrUpdateRolePermissionOutputDto {

    private String message;
    private String status;

    public CreateOrUpdateRolePermissionOutputDto(String message, String status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    
    
}
