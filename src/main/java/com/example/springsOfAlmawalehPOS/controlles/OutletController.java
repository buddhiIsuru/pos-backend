package com.example.springsOfAlmawalehPOS.controlles;

import com.example.springsOfAlmawalehPOS.modal.CategoryModal;
import com.example.springsOfAlmawalehPOS.modal.OutletModal;
import com.example.springsOfAlmawalehPOS.repositories.OutletRepository;
import com.example.springsOfAlmawalehPOS.services.CategoryService;
import com.example.springsOfAlmawalehPOS.services.OutletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/outlet")
@CrossOrigin
public class OutletController {
    @Autowired
    private OutletService outletService;

    @PostMapping("/save-outlet")
    public ResponseEntity<OutletModal> saveOutlet(@RequestBody OutletModal outletModal){
        return outletService.saveOutlet(outletModal);
    }

    @GetMapping("/get-all-outlet")
    public List<OutletModal> getAllOutlet(){
        return outletService.getAllOutlet();
    }

}
