package com.example.springsOfAlmawalehPOS.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Charges {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String chargesName;
    private Double chargesAmount;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "invoice_id",referencedColumnName = "id")
    private Invoice invoice;

}
