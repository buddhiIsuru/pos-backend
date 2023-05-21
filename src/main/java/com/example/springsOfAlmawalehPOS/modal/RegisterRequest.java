package com.example.springsOfAlmawalehPOS.modal;

import com.example.springsOfAlmawalehPOS.entity.Outlet;
import com.example.springsOfAlmawalehPOS.permission.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
//  private String firstname;
//  private String lastname;
//  private String email;
//  private String password;
//  private Role role;

  private Long id;
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
