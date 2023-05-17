package com.example.springsOfAlmawalehPOS.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InvoiceDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer product_qty;
    private Double product_amount;
    private Double product_discount;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private Long productId;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "product_id",referencedColumnName = "id")
//    private Product productId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "invoice_id",referencedColumnName = "id")
    private Invoice invoice;

}
