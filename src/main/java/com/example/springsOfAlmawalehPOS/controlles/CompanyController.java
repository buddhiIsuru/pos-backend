package com.example.springsOfAlmawalehPOS.controlles;

import com.example.springsOfAlmawalehPOS.modal.CompanyModal;
import com.example.springsOfAlmawalehPOS.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/company")
@CrossOrigin
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @PostMapping("/save-company")
    public CompanyModal saveCategory(@RequestBody CompanyModal companyModal){
        return companyService.saveCompany(companyModal);
    }

    @GetMapping("/get-company")
    public List<CompanyModal> getCategory(){
        return companyService.getCompany();
    }
}
