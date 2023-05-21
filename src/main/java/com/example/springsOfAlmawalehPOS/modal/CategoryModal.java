package com.example.springsOfAlmawalehPOS.modal;

import com.example.springsOfAlmawalehPOS.entity.Outlet;
import com.example.springsOfAlmawalehPOS.entity.SubCategory;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryModal {
    private Long id;
    private String name;
    private Long outletId;
}
