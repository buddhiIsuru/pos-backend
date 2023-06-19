package com.example.springsOfAlmawalehPOS.modal;

import com.example.springsOfAlmawalehPOS.entity.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceModal {
    private Long id;
    private String remark;
    private Long outletId;
    private Long userId;
    private Outlet outlet;
    private User user;
    private Long customerId;
    private String customerVehicleNo;
    private Boolean is_draft;
    private String invoiceId;
    private Integer total_qty;
    private Customer customer;
    private Double tax_amount;
    private String invoiceType;
    private Double subTotalAmount;
    private String payment_method;
    private Double total_discount;
    private Double grandTotalAmount;
    private Set<Charges> expensesList;
    private LocalDateTime createdAt;
    private Set<InvoiceDetail> invoiceDetails;
    private Set<InvoiceDetailModal> invoiceDetailsDetailModals;
}
