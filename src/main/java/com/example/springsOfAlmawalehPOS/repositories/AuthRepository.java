package com.example.springsOfAlmawalehPOS.repositories;


import com.example.springsOfAlmawalehPOS.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AuthRepository extends CrudRepository<User,Long>, PagingAndSortingRepository<User, Long> {
    User findUserByUsernameAndPassword(String username,String password);

    Boolean existsByUsername(String username);

}
