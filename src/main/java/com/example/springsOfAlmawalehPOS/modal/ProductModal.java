package com.example.springsOfAlmawalehPOS.modal;

import com.example.springsOfAlmawalehPOS.entity.Category;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductModal {
    private Long id;
    private String name;
    private Double price;
    private Double discount;
    private String product_code;
    private String unit_type;
    private Long categoryId;
    private Category category;
    private CategoryModal categoryModal;
    private String imageUrl;
    private Long imageId;
    private Boolean taxIncluded;
}
