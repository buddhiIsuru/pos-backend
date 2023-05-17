package com.example.springsOfAlmawalehPOS.repositories;

import com.example.springsOfAlmawalehPOS.entity.Category;
import com.example.springsOfAlmawalehPOS.entity.Outlet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OutletRepository extends CrudRepository<Outlet,Long>, PagingAndSortingRepository<Outlet, Long> {
    Outlet findFirstById(Long id);
}
