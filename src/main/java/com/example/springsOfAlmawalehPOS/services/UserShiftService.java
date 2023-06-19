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

    public UserShiftModal checkUserShiftRepository(Long id) {
        UserShift isDefine = userShiftRepository.findFirstByUserAndStatus(userRepository.findFirstById(id), true);
        UserShiftModal userShiftModal = null;
        if (isDefine != null) {
            userShiftModal =
                    UserShiftModal.
                            builder().
                            start_at(isDefine.getStart_at()).
                            close_at(isDefine.getClose_at()).
                            id(isDefine.getId()).
                            status(true).
                            startAmount(shiftCashManagementRepository.findFirstByUserShift(isDefine).getStartAmount()).
                            closeAmount(shiftCashManagementRepository.findFirstByUserShift(isDefine).getCloseAmount()).
                            build();
        } else {
            userShiftModal =
                    UserShiftModal.
                            builder().
                            id(0L).
                            status(false).
                            build();
        }
        return userShiftModal;
    }

    public UserShiftModal startUserShiftRepository(UserShiftModal userShiftModal) {
        UserShift isDefine = userShiftRepository.findFirstByUserAndStatus(userRepository.findFirstById(userShiftModal.getId()), true);
        if (isDefine == null) {
            UserShift userShift =
                    UserShift
                            .builder()
                            .user(userRepository.findFirstById(userShiftModal.getUserId()))
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
                shiftCashManagementRepository.save(shiftCashManagement);
            }
            return UserShiftModal.
                    builder().
                    start_at(userShiftSaved.getStart_at()).
                    id(userShiftSaved.getId()).
                    status(true).
                    startAmount(shiftCashManagementRepository.findFirstByUserShift(userShiftSaved).getStartAmount()).
                    build();
        }
        return
                UserShiftModal.
                        builder().
                        start_at(isDefine.getStart_at()).
                        id(isDefine.getId()).
                        status(true).
                        startAmount(shiftCashManagementRepository.findFirstByUserShift(isDefine).getStartAmount()).
                        build();

    }

    public Long closeUserShiftRepository(UserShiftModal userShiftModal) {
        UserShift userShift = userShiftRepository.findFirstByUserAndStatus(userRepository.findFirstById(userShiftModal.getUserId()), true);

        if (userShift != null) {
            userShift.setClose_at(LocalDateTime.now());
            userShift.setStatus(false);

            UserShift userShiftSaved = userShiftRepository.save(userShift);

            if (userShiftModal.getId() != null) {
                ShiftCashManagement shiftCashManagement = shiftCashManagementRepository.findFirstByUserShift(userShiftRepository.findFirstById(userShiftModal.getId()));
                shiftCashManagement.setCloseAmount(userShiftModal.getCloseAmount());
                shiftCashManagementRepository.save(shiftCashManagement);
            }
            return userShiftSaved.getId();
        }
        return null;

    }


}
