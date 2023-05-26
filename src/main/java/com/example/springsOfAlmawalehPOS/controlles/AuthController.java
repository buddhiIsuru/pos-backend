package com.example.springsOfAlmawalehPOS.controlles;

import com.example.springsOfAlmawalehPOS.modal.AuthModal;
import com.example.springsOfAlmawalehPOS.modal.RoleModal;
import com.example.springsOfAlmawalehPOS.modal.ShiftManagementModal;
import com.example.springsOfAlmawalehPOS.modal.UserModal;
import com.example.springsOfAlmawalehPOS.services.AuthService;
import com.example.springsOfAlmawalehPOS.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/auth")
@CrossOrigin
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private RoleService roleService;

    @PostMapping("/register")
    public UserModal userRegister(@RequestBody UserModal userModal){
        return  authService.registerUser(userModal);
    }

    @PostMapping("/login")
    public UserModal userRegister(@RequestBody AuthModal authModal){
        return  authService.loginUser(authModal);
    }

    @PostMapping("/save-role")
    public RoleModal roleCreate(@RequestBody RoleModal roleModal){
        return  roleService.registerRole(roleModal);
    }

    @GetMapping("/get-all-role")
    public List<RoleModal> getAll(){
        return  roleService.getAllRole();
    }

    @GetMapping("/get-all-users/{id}")
    public List<UserModal> getAllUsers(@PathVariable("id") Long id){
        return  authService.getAllUsers(id);
    }

    @GetMapping("/close-store/{id}")
    public ShiftManagementModal closeStore(@PathVariable("id") Long id){
        return  authService.closeStore(id);
    }

    @GetMapping("/get-outlet-shift/{id}")
    public List<ShiftManagementModal>  getOutletShift(@PathVariable("id") Long id){
        return  authService.getOutletShift(id);
    }
}
