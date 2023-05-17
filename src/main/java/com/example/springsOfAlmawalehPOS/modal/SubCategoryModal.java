package com.example.springsOfAlmawalehPOS.modal;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SubCategoryModal {
    private Long id;
    private String name;
    private Long categoryId;
}
