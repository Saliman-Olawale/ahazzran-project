package ci.orange.digital.center.ahazzran.webservice.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import ci.orange.digital.center.ahazzran.webservice.dtos.CreateOrUpdateRoleInputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.CreateOrUpdateRolePermissionInputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.PermissionOutputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.RoleOutputDto;
import ci.orange.digital.center.ahazzran.webservice.dtos.RolePermissionOutputDto;
import ci.orange.digital.center.ahazzran.webservice.services.IPermissionService;
import ci.orange.digital.center.ahazzran.webservice.services.IRolePermissionService;
import ci.orange.digital.center.ahazzran.webservice.services.IRoleService;

@RestController
@RequestMapping("/api/roles")

public class RoleController {

    private IRoleService roleService;

    private IRolePermissionService rolePermissionService;
    private IPermissionService permissionService;

    public RoleController(final IRoleService roleService, final IRolePermissionService rolePermissionService, final IPermissionService permissionService) {
        this.roleService = roleService;
        this.rolePermissionService = rolePermissionService;
        this.permissionService = permissionService;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<RoleOutputDto>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @PostMapping("/createorupdate")
    public ResponseEntity<?> createOrUpdateRole(@RequestBody CreateOrUpdateRoleInputDto input) {
        return ResponseEntity.ok(roleService.createOrUpdateRole(input));
    }

    @PostMapping("/disable/{roleId}")
    public ResponseEntity<?> disableRole(@PathVariable int roleId) {
        return ResponseEntity.ok(roleService.disableRole(roleId));
    }
    @PostMapping("/enable/{roleId}")
    public ResponseEntity<?> enableRole(@PathVariable int roleId) {
        return ResponseEntity.ok(roleService.enableRole(roleId));
    }
    


    @GetMapping("/getpermissionbyrole/{roleId}")
    public ResponseEntity<RolePermissionOutputDto> getPermissionsByRoleId(@PathVariable int roleId) {
        return ResponseEntity.ok(rolePermissionService.getPermissionsByRoleId(roleId));
    }

    @PostMapping("/permissions/createorupdate")
    public ResponseEntity<?> createOrUpdateRolePermission(@RequestBody CreateOrUpdateRolePermissionInputDto input) {
        return ResponseEntity.ok(rolePermissionService.createOrUpdateRolePermission(input));
    }

    @PostMapping("/permissions/disable/{rolePermissionId}")
    public ResponseEntity<?> disableRolePermission(@PathVariable int rolePermissionId) {
        return ResponseEntity.ok(rolePermissionService.disableRolePermission(rolePermissionId));
    }
    @PostMapping("/permissions/enable/{rolePermissionId}")
    public ResponseEntity<?> enableRolePermission(@PathVariable int rolePermissionId) {
        return ResponseEntity.ok(rolePermissionService.enableRolePermission(rolePermissionId));
    }


    @GetMapping("/permissions/getallpermissions")
    public ResponseEntity<List<PermissionOutputDto>> getAllPermissions() {
        return ResponseEntity.ok(permissionService.getAllPermissions());
    }

}
