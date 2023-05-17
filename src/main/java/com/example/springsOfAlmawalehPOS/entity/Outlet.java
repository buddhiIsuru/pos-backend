package com.example.springsOfAlmawalehPOS.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Outlet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String outletName;
    private String address;
    private String phoneNo;
    private Long logoId;

    @Column(name = "CREATE_DATE")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id",referencedColumnName = "id")
    private Company company;

    @OneToMany(mappedBy = "outlet")
    private Set<Category> categories;

    @OneToMany(mappedBy = "outlet")
    private Set<Customer> customers;

    @OneToMany(mappedBy = "outlet")
    private Set<Invoice> invoices;

    @OneToMany(mappedBy = "outlet")
    private Set<User> userSet;

    @OneToMany(mappedBy = "outlet")
    private Set<ShiftManagement> shiftManagement;

}
