package com.example.springsOfAlmawalehPOS.controlles;

import com.example.springsOfAlmawalehPOS.modal.CustomerModal;
import com.example.springsOfAlmawalehPOS.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/customer")
@CrossOrigin
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/save-customer")
    public CustomerModal saveCustomer(@RequestBody CustomerModal customerModal){
        return customerService.saveCustomer(customerModal);
    }

    @GetMapping("/get-all-customer")
    public List<CustomerModal> getAllCustomer(){
        return customerService.getAllCustomer();
    }

    @GetMapping("/find-by-id-customer/{id}")
    public List<CustomerModal> findByCustomerId(@PathVariable("id") Long id){
        return customerService.findByCustomerId(id);
    }

    @PutMapping("/update-customer/{id}")
    public CustomerModal getAllCustomer(@PathVariable("id") Long Id,@RequestBody CustomerModal customerModal){
        return customerService.updateCustomer(Id,customerModal);
    }

    @DeleteMapping("/delete-customer/{id}")
    public String deleteCustomer(@PathVariable("id") Long id){
        return customerService.deleteCustomer(id);
    }
}
