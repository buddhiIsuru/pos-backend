package com.example.springsOfAlmawalehPOS.services;

import com.example.springsOfAlmawalehPOS.entity.ShiftCashManagement;
import com.example.springsOfAlmawalehPOS.entity.UserShift;
import com.example.springsOfAlmawalehPOS.modal.UserShiftModal;
import com.example.springsOfAlmawalehPOS.repositories.ShiftCashManagementRepository;
import com.example.springsOfAlmawalehPOS.repositories.UserRepository;
import com.example.springsOfAlmawalehPOS.repositories.UserShiftRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserShiftService {
    private final UserShiftRepository userShiftRepository;
    private final UserRepository userRepository;
    private final ShiftCashManagementRepository shiftCashManagementRepository;

    private UserShiftService(UserShiftRepository userShiftRepository, UserRepository userRepository, ShiftCashManagementRepository shiftCashManagementRepository) {
        this.userShiftRepository = userShiftRepository;
        this.userRepository = userRepository;
        this.shiftCashManagementRepository = shiftCashManagementRepository;
    }

    public UserShift checkUserShiftRepository(Long id) {
        UserShift isDefine = userShiftRepository.findFirstByUserAndStatus(id,true);
        return  isDefine;

    }

    public UserShift startUserShiftRepository(UserShiftModal userShiftModal) {
        UserShift isDefine = userShiftRepository.findFirstByUserAndStatus(userShiftModal.getId(),true);
        if (isDefine == null) {
            UserShift userShift =
                    UserShift
                            .builder()
                            .user(userRepository.findFirstById(userShiftModal.getId()))
                            .start_at(LocalDateTime.now())
                            .status(true)
                            .build();
            UserShift userShiftSaved = userShiftRepository.save(userShift);
            if (userShiftSaved.getId() != null) {
                ShiftCashManagement shiftCashManagement = ShiftCashManagement
                        .builder()
                        .userShift(userShiftSaved)
                        .startAmount(userShiftModal.getStartAmount())
                        .build();
            }
            return userShiftSaved;
        }
        return  isDefine;

    }

    public Long closeUserShiftRepository(UserShiftModal userShiftModal) {
        UserShift userShift = userShiftRepository.findFirstById(userShiftModal.getId());

        userShift.setClose_at(LocalDateTime.now());
        userShift.setStatus(false);

        UserShift userShiftSaved = userShiftRepository.save(userShift);

        if (userShiftModal.getId() != null) {
            ShiftCashManagement shiftCashManagement = shiftCashManagementRepository.findFirstByUserShift(userShiftModal.getId());
            shiftCashManagement.setCloseAmount(userShiftModal.getCloseAmount());
        }
        return userShiftSaved.getId();
    }


}
