package com.example.springsOfAlmawalehPOS.modal;

import com.example.springsOfAlmawalehPOS.entity.Outlet;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RoleModal {
    private Long id;
    private String name;
}
