package com.example.springsOfAlmawalehPOS.controlles;


import com.example.springsOfAlmawalehPOS.entity.UserShift;
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
    public ResponseEntity<UserShiftModal> startUserShift(@RequestBody UserShiftModal userShiftModal) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userShiftService.startUserShiftRepository(userShiftModal));
    }

    @GetMapping("/check-shift/{id}")
    public UserShiftModal checkUserShift(@PathVariable("id") Long id ) {
        return userShiftService.checkUserShiftRepository(id);
//        return ResponseEntity.status(HttpStatus.OK).body(userShiftService.checkUserShiftRepository(id));
    }

    @PostMapping("/close-shift")
    public ResponseEntity<Long> closeUserShift(@RequestBody UserShiftModal userShiftModal) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userShiftService.closeUserShiftRepository(userShiftModal));
    }


}
