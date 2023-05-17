package com.example.springsOfAlmawalehPOS.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer payment_method;
    private Double payment_amount;

    @Column(name = "PAYMENT_DATE")
    private java.sql.Date startDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "invoice_id",referencedColumnName = "id")
    private Invoice invoice;

}
