package com.example.springsOfAlmawalehPOS.repositories;

import com.example.springsOfAlmawalehPOS.entity.Category;
import com.example.springsOfAlmawalehPOS.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product,Long>, PagingAndSortingRepository<Product, Long> {
    List<Product> findAllByCategory(Category category);
    Product findFirstById(Long id);
}
