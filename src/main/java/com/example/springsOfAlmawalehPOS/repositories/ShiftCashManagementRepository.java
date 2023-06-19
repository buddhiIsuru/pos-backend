package com.example.springsOfAlmawalehPOS.repositories;

import com.example.springsOfAlmawalehPOS.entity.ShiftCashManagement;
import com.example.springsOfAlmawalehPOS.entity.UserShift;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShiftCashManagementRepository extends JpaRepository<ShiftCashManagement,Long> {
    ShiftCashManagement findFirstByUserShift(UserShift userShift);
}
