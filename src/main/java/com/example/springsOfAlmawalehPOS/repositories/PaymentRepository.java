package com.example.springsOfAlmawalehPOS.repositories;

import com.example.springsOfAlmawalehPOS.entity.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PaymentRepository extends CrudRepository<Payment,Long>, PagingAndSortingRepository<Payment, Long> { }
