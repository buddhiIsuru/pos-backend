package com.example.springsOfAlmawalehPOS.controlles;

import com.example.springsOfAlmawalehPOS.modal.*;
import com.example.springsOfAlmawalehPOS.services.AuthService;
import com.example.springsOfAlmawalehPOS.services.RoleService;
import com.example.springsOfAlmawalehPOS.services.impl.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/auth")
@CrossOrigin
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private RoleService roleService;

    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/user-register")
    public UserModal userRegister(@RequestBody UserModal userModal) {
        return authService.registerUser(userModal);
    }

    @PostMapping("/login")
    public UserModal userRegister(@RequestBody AuthModal authModal) {
        return authService.loginUser(authModal);
    }

    @PostMapping("/save-role")
    public RoleModal roleCreate(@RequestBody RoleModal roleModal) {
        return roleService.registerRole(roleModal);
    }

    @GetMapping("/get-all-role")
    public List<RoleModal> getAll() {
        return roleService.getAllRole();
    }

    @GetMapping("/get-all-users/{id}")
    public List<UserModal> getAllUsers(@PathVariable("id") Long id) {
        return authService.getAllUsers(id);
    }

    @GetMapping("/close-store/{id}")
    public ShiftManagementModal closeStore(@PathVariable("id") Long id) {
        return authService.closeStore(id);
    }

    @GetMapping("/get-outlet-shift/{id}")
    public List<ShiftManagementModal> getOutletShift(@PathVariable("id") Long id) {
        return authService.getOutletShift(id);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        authenticationService.refreshToken(request, response);
    }
}
