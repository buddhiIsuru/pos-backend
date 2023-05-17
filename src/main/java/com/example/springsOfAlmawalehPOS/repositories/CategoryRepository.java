package com.example.springsOfAlmawalehPOS.repositories;

import com.example.springsOfAlmawalehPOS.entity.Category;
import com.example.springsOfAlmawalehPOS.entity.Outlet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category,Long>, PagingAndSortingRepository<Category, Long> {
    Category findFirstById(Long id);

    Category findFirstById(Category id);
    List<Category> findAllByOutlet(Outlet id);
}
