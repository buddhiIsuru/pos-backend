package com.example.springsOfAlmawalehPOS.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Column(name = "CREATE_DATE")
    private java.sql.Date startDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoryId", referencedColumnName = "id")
    private Category category;



}
