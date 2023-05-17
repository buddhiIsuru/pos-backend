package com.example.springsOfAlmawalehPOS.services;

import com.example.springsOfAlmawalehPOS.entity.Category;
import com.example.springsOfAlmawalehPOS.entity.Role;
import com.example.springsOfAlmawalehPOS.entity.User;
import com.example.springsOfAlmawalehPOS.modal.AuthModal;
import com.example.springsOfAlmawalehPOS.modal.CategoryModal;
import com.example.springsOfAlmawalehPOS.modal.RoleModal;
import com.example.springsOfAlmawalehPOS.modal.UserModal;
import com.example.springsOfAlmawalehPOS.repositories.AuthRepository;
import com.example.springsOfAlmawalehPOS.repositories.OutletRepository;
import com.example.springsOfAlmawalehPOS.repositories.RoleRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;

    public RoleModal registerRole(RoleModal roleModal) {
        Role role=new Role();
        role.setName(roleModal.getName());
        Role roleSave=roleRepository.save(role);
        RoleModal returnRoleModal=new RoleModal();
        returnRoleModal.setId(roleSave.getId());
        returnRoleModal.setName(roleSave.getName());
        return returnRoleModal;
    }

    public List<RoleModal> getAllRole() {
        List<Role> roleList=roleRepository.findAll();
        return modelMapper.map(roleList,new TypeToken<List<RoleModal>>(){}.getType());
    }
}
