package com.example.springsOfAlmawalehPOS.modal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OutletModal {
    private Long id;
    private Long logoId;
    private String outletName;
    private String address;
    private String phoneNo;
    private Long companyId;
}
