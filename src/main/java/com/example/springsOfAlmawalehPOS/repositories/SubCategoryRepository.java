package com.example.springsOfAlmawalehPOS.repositories;

import com.example.springsOfAlmawalehPOS.entity.SubCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SubCategoryRepository extends CrudRepository<SubCategory,Long>, PagingAndSortingRepository<SubCategory, Long> { }
