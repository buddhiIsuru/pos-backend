package com.example.springsOfAlmawalehPOS.controlles;

import com.example.springsOfAlmawalehPOS.modal.CompanyModal;
import com.example.springsOfAlmawalehPOS.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/company")
@CrossOrigin
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @PostMapping("/save-company")
    public CompanyModal saveCategory(@RequestBody CompanyModal companyModal){
        return companyService.saveCompany(companyModal);
    }
}
