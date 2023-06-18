package com.example.springsOfAlmawalehPOS.repositories;

import com.example.springsOfAlmawalehPOS.entity.User;
import com.example.springsOfAlmawalehPOS.entity.UserShift;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserShiftRepository extends JpaRepository<UserShift, Long> {

    UserShift findFirstById(Long id);
    UserShift findFirstByUserAndStatus(Long id,boolean status);
}
