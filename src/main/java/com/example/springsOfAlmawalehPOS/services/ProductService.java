package com.example.springsOfAlmawalehPOS.services;

import com.example.springsOfAlmawalehPOS.entity.Category;
import com.example.springsOfAlmawalehPOS.entity.Product;
import com.example.springsOfAlmawalehPOS.modal.CategoryModal;
import com.example.springsOfAlmawalehPOS.modal.ProductModal;
import com.example.springsOfAlmawalehPOS.repositories.CategoryRepository;
import com.example.springsOfAlmawalehPOS.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Long saveProduct(ProductModal productModal) {
        Product product = new Product();
        product.setId(productModal.getId());
        product.setName(productModal.getName());
        product.setPrice(productModal.getPrice());
        product.setDiscount(productModal.getDiscount());
        product.setProduct_code(productModal.getProduct_code());
        product.setUnit_type(productModal.getUnit_type());
        product.setImageId(productModal.getImageId());
        product.setTaxIncluded(productModal.getTaxIncluded());
        product.setCategory(categoryRepository.findFirstById(productModal.getCategoryId()));
        Product product1 = productRepository.save(product);
        return product1.getId();
    }

    public List<ProductModal> getAllProduct() {
        List<Product> productList = (List<Product>) productRepository.findAll();
//        List<Product> productList= (List<Product>) productRepository.findAll();
        List<ProductModal> productModalList = new ArrayList<ProductModal>();

        for (Product product : productList) {
            ProductModal productModal = new ProductModal();
            productModal.setId(product.getId());
            productModal.setName(product.getName());
            productModal.setPrice(product.getPrice());
            productModal.setDiscount(product.getDiscount());
            productModal.setProduct_code(product.getProduct_code());
            productModal.setUnit_type(product.getUnit_type());
            productModal.setImageId(product.getImageId());
            productModal.setTaxIncluded(product.getTaxIncluded());
            productModal.setCategoryId(product.getCategory().getId());
            productModalList.add(productModal);
        }

        return productModalList;
//        return modelMapper.map(productList,new TypeToken<List<ProductModal>>(){}.getType());
    }

    public List<ProductModal> getAllByCategory(Long id) {
        Category category = categoryRepository.findFirstById(id);
        List<Product> productList = productRepository.findAllByCategory(category);
//        List<Product> productList= (List<Product>) productRepository.findAll();
        List<ProductModal> productModalList = new ArrayList<ProductModal>();

        for (Product product : productList) {
            ProductModal productModal = new ProductModal();
            productModal.setId(product.getId());
            productModal.setName(product.getName());
            productModal.setPrice(product.getPrice());
            productModal.setDiscount(product.getDiscount());
            productModal.setProduct_code(product.getProduct_code());
            productModal.setUnit_type(product.getUnit_type());
            productModal.setImageId(product.getImageId());
            productModal.setTaxIncluded(product.getTaxIncluded());
            productModalList.add(productModal);
        }

        return productModalList;
//        return modelMapper.map(productList,new TypeToken<List<ProductModal>>(){}.getType());
    }

    public ProductModal getProductsById(Long id) {
        Product product = productRepository.findFirstById(id);
        ProductModal productModal = new ProductModal();

        productModal.setId(product.getId());
        productModal.setName(product.getName());
        productModal.setPrice(product.getPrice());
        productModal.setDiscount(product.getDiscount());
        productModal.setProduct_code(product.getProduct_code());
        productModal.setUnit_type(product.getUnit_type());
        productModal.setCategoryId(product.getCategory().getId());
        productModal.setImageId(product.getImageId());
        productModal.setTaxIncluded(product.getTaxIncluded());
        productModal.setCategoryModal(modelMapper.map(categoryRepository.findFirstById(product.getCategory().getId()),CategoryModal.class));

        return productModal;
    }

    public ProductModal updateProduct(Long id, ProductModal customerModal) {
        Optional<Product> alreadyCustomer = productRepository.findById(id);

//        if(alreadyCustomer.isPresent()){
//            Customer customer=alreadyCustomer.get();
//            customer.setId(id);
//            customer.setName(customerModal.getName());
//            customer.setEmail(customerModal.getEmail());
//            customer.setAddress(customerModal.getAddress());
//            customer.setVehicleNo(customerModal.getVehicleNo());
//            return modelMapper.map(ProductRepository.save(customer),CustomerModal.class);
//        }
        return null;
    }

    public String deleteProduct(Long id) {
        productRepository.deleteById(id);
        return "";
    }

    public List<ProductModal> findByProductId(Long id) {
        Optional<Product> ProductList = productRepository.findById(id);
        return modelMapper.map(ProductList, new TypeToken<List<ProductModal>>() {
        }.getType());
    }
}
