package ci.orange.digital.center.ahazzran.webservice.dtos;

import ci.orange.digital.center.ahazzran.webservice.entities.Role;

public class RoleOutputDto {
    
    private int id;
    
    private String designation;
    private String codeRole;
    private boolean active;
    
    public RoleOutputDto(Role role) {
        
        this.id = role.getRoleId();
        this.designation = role.getDesignation();
        this.codeRole = role.getCodeRole();
        this.active = role.getStatut()!= null? role.getStatut().equals("ENABLE") : false;
    }

    public int getId() {
        return id;
    }

    public String getDesignation() {
        return designation;
    }

    public String getCodeRole() {
        return codeRole;
    }

    public boolean isActive() {
        return active;
    }

    
}
