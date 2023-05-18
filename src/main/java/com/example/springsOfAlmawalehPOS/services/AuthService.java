package com.example.springsOfAlmawalehPOS.services;

import com.example.springsOfAlmawalehPOS.entity.ShiftManagement;
import com.example.springsOfAlmawalehPOS.entity.User;
import com.example.springsOfAlmawalehPOS.modal.*;
import com.example.springsOfAlmawalehPOS.repositories.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private OutletRepository outletRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ShiftRepository shiftRepository;

    public UserModal registerUser(UserModal userModal) {
        User user = new User();
        UserModal returnModal = new UserModal();
        if (!authRepository.existsByUsername(userModal.getUsername())) {
            user.setUsername(userModal.getUsername());
            user.setPassword(userModal.getPassword());
            user.setRole(roleRepository.findFirstById(userModal.getRoleId()));
            user.setOutlet(outletRepository.findFirstById(userModal.getOutletId()));
            User userSave = authRepository.save(user);
            returnModal.setPassword(userSave.getPassword());
            returnModal.setUsername(userSave.getUsername());
        }
        return returnModal;
    }

    public UserModal loginUser(AuthModal authModal) {
        UserModal returnModal = new UserModal();
        User user = authRepository.findUserByUsernameAndPassword(authModal.getUserName(), authModal.getPassword());
        if (user != null) {
            RoleModal roleModal = modelMapper.map(roleRepository.findFirstById(user.getRole().getId()), RoleModal.class);
            OutletModal outletModal = modelMapper.map(outletRepository.findFirstById(user.getOutlet().getId()), OutletModal.class);
            returnModal.setPassword(user.getPassword());
            returnModal.setUsername(user.getUsername());
            returnModal.setRoleModal(roleModal);
            if (outletModal != null) {
                returnModal.setOutletModal(outletModal);
            }
            if (Objects.equals(roleModal.getName(), "USER")) {
                ShiftManagement shift = shiftRepository.findFirstByOutletAndStatus(user.getOutlet(), true);
                if (shift == null) {
                    shift = new ShiftManagement();
                }
//                ShiftManagement shift = shiftRepository.findFirstByOutlet(user.getOutlet());
                shift.setOutlet(user.getOutlet());
                shift.setStart_at(LocalDateTime.now());
                shift.setStatus(true);
                ShiftManagement shiftSave = shiftRepository.save(shift);
                returnModal.setShiftId(shiftSave.getId());
            }
        }
        return returnModal;
    }

    public ShiftManagementModal closeStore(Long id) {
        ShiftManagement shift = shiftRepository.findFirstById(id);
        shift.setId(id);
        shift.setStatus(false);
        shift.setClose_at(LocalDateTime.now());
        ShiftManagement shiftSave = shiftRepository.save(shift);
        ShiftManagementModal shiftManagementModal = new ShiftManagementModal();
        shiftManagementModal.setId(shiftSave.getId());
        return shiftManagementModal;
    }

    public List<UserModal> getAllUsers(Long id) {
        List<User> userList = userRepository.findByOutlet(outletRepository.findFirstById(id));
        List<UserModal> userModalList = new ArrayList<>();
        for (User user : userList) {
            UserModal userModal = new UserModal();
            userModal.setUsername(user.getUsername());
            userModal.setPassword(user.getPassword());
            userModal.setRoleModal(modelMapper.map(roleRepository.findFirstById(user.getRole().getId()), RoleModal.class));
            userModalList.add(userModal);
        }
        return userModalList;
    }

    public List<ShiftManagementModal> getOutletShift(Long id) {
        List<ShiftManagement> shiftManagementList = shiftRepository.findByOutlet(outletRepository.findFirstById(id));
        List<ShiftManagementModal> shiftManagementModals = new ArrayList<>();
        for (ShiftManagement shiftManagement : shiftManagementList) {
            ShiftManagementModal shiftManagementModal = new ShiftManagementModal();
            shiftManagementModal.setId(shiftManagement.getId());
            shiftManagementModal.setClose_at(shiftManagement.getClose_at());
            shiftManagementModal.setStart_at(shiftManagement.getStart_at());
            shiftManagementModals.add(shiftManagementModal);
        }
        return shiftManagementModals;
    }
}
