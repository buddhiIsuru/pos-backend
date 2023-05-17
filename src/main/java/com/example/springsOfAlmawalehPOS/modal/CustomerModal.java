package com.example.springsOfAlmawalehPOS.modal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerModal {
    private Long id;
    private String name;
    private String email;
    private String address;
    private String contactNo;
    private String vehicleNo;
}
