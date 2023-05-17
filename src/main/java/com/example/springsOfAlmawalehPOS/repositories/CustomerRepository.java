package com.example.springsOfAlmawalehPOS.repositories;

import com.example.springsOfAlmawalehPOS.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerRepository extends CrudRepository<Customer,Long>, PagingAndSortingRepository<Customer, Long> {
    Customer findByVehicleNo(String customerVehicleNo);
}
