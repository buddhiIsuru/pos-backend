package com.example.springsOfAlmawalehPOS.repositories;

import com.example.springsOfAlmawalehPOS.entity.Category;
import com.example.springsOfAlmawalehPOS.entity.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CompanyRepository extends CrudRepository<Company,Long>, PagingAndSortingRepository<Company, Long> {
    Company findFirstById(Long id);
}
