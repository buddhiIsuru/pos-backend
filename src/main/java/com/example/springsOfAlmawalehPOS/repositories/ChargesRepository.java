package com.example.springsOfAlmawalehPOS.repositories;

import com.example.springsOfAlmawalehPOS.entity.Charges;
import com.example.springsOfAlmawalehPOS.entity.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ChargesRepository extends CrudRepository<Charges,Long>, PagingAndSortingRepository<Charges, Long> { }
