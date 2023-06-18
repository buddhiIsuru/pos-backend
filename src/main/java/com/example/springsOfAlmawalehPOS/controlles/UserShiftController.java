package com.example.springsOfAlmawalehPOS.controlles;


import com.example.springsOfAlmawalehPOS.modal.UserShiftModal;
import com.example.springsOfAlmawalehPOS.services.UserShiftService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/user-shift")
@CrossOrigin
public class UserShiftController {

    private final UserShiftService userShiftService;

    public UserShiftController(UserShiftService userShiftService) {
        this.userShiftService = userShiftService;
    }

    @PostMapping("/start-shift")
    public ResponseEntity<Long> startUserShiftRepository(@RequestBody UserShiftModal userShiftModal) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userShiftService.startUserShiftRepository(userShiftModal));
    }

    @PostMapping("/close-shift")
    public ResponseEntity<Long> closeUserShiftRepository(@RequestBody UserShiftModal userShiftModal) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userShiftService.closeUserShiftRepository(userShiftModal));
    }


}
