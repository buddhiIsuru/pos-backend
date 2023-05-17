package com.example.springsOfAlmawalehPOS.repositories;


import com.example.springsOfAlmawalehPOS.entity.Outlet;
import com.example.springsOfAlmawalehPOS.entity.ShiftManagement;
import com.example.springsOfAlmawalehPOS.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShiftRepository extends JpaRepository<ShiftManagement, Long> {

    //    @Query(
//            value = "SELECT * FROM shift_management WHERE outlet_id=:outletID and status=:isClose  order by id desc limit 1;",
//            nativeQuery = true)
    ShiftManagement findFirstByOutletAndStatus(Outlet outlet, boolean isClose);

    //    ShiftManagement findFirstByOutletAndStatus(Long outletID,boolean isClose);
    List<ShiftManagement> findFirstByOutlet(Outlet outlet);

    List<ShiftManagement> findByOutlet(Outlet outlet);

    ShiftManagement findFirstById(Long id);

}