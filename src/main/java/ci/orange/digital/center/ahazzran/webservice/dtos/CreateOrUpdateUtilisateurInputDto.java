package ci.orange.digital.center.ahazzran.webservice.dtos;


public class CreateOrUpdateUtilisateurInputDto {
    
    private int id;
    private String email;
    private int roleId;

    public CreateOrUpdateUtilisateurInputDto() {
    }
    
    public int getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    public int getRoleId() {
        return roleId;
    }


}
