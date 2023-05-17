package com.example.springsOfAlmawalehPOS.services;

import com.example.springsOfAlmawalehPOS.entity.Customer;
import com.example.springsOfAlmawalehPOS.modal.CustomerModal;
import com.example.springsOfAlmawalehPOS.repositories.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    public CustomerModal saveCustomer(CustomerModal customerModal){
        Customer customer= customerRepository.save(modelMapper.map(customerModal,Customer.class));
        return modelMapper.map(customer,CustomerModal.class);
    }

    public List<CustomerModal> getAllCustomer(){
        List<Customer> customerList= (List<Customer>) customerRepository.findAll();
        return modelMapper.map(customerList,new TypeToken<List<CustomerModal>>(){}.getType());
    }

    public CustomerModal updateCustomer(Long id,CustomerModal customerModal){
        Optional<Customer> alreadyCustomer=customerRepository.findById(id);

        if(alreadyCustomer.isPresent()){
            Customer customer=alreadyCustomer.get();
            customer.setId(id);
            customer.setName(customerModal.getName());
            customer.setEmail(customerModal.getEmail());
            customer.setAddress(customerModal.getAddress());
            customer.setVehicleNo(customerModal.getVehicleNo());
            return modelMapper.map(customerRepository.save(customer),CustomerModal.class);
        }
        return null;
    }

    public String deleteCustomer(Long id) {
        customerRepository.deleteById(id);
        return "";
    }

    public List<CustomerModal> findByCustomerId(Long id) {
        Optional<Customer> customerList= customerRepository.findById(id);
        return modelMapper.map(customerList,new TypeToken<List<CustomerModal>>(){}.getType());
    }
}
