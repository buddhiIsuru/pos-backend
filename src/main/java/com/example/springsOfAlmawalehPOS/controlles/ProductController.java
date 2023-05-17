package com.example.springsOfAlmawalehPOS.controlles;

import com.example.springsOfAlmawalehPOS.entity.Product;
import com.example.springsOfAlmawalehPOS.modal.ProductModal;
import com.example.springsOfAlmawalehPOS.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/product")
@CrossOrigin
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/save-product")
    public Long productSave(@RequestBody ProductModal productModal){
        return productService.saveProduct(productModal);
    }

    @GetMapping("/get-all-product")
    public List<ProductModal> getAllProducts(){
        return productService.getAllProduct();
    }

    @GetMapping("/get-all-product-by-category/{id}")
    public List<ProductModal> getAllProductsByCategory(@PathVariable("id") Long id){
        return productService.getAllByCategory(id);
    }

    @GetMapping("/get-product-by-id/{id}")
    public ProductModal getProductsById(@PathVariable("id") Long id){
        return productService.getProductsById(id);
    }

}
