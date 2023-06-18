package com.example.springsOfAlmawalehPOS.modal;

import com.example.springsOfAlmawalehPOS.entity.Outlet;
import com.example.springsOfAlmawalehPOS.entity.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserModal {
    private Long id;
    private Long userId;
    private String username;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Outlet outlet;
    private OutletModal outletModal;
    private Long outletId;
    private Long roleId;
    private Long shiftId;
    private Role role;
    private RoleModal roleModal;
}
