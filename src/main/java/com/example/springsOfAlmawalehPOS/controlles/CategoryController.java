package com.example.springsOfAlmawalehPOS.controlles;

import com.example.springsOfAlmawalehPOS.modal.CategoryModal;
import com.example.springsOfAlmawalehPOS.modal.CustomerModal;
import com.example.springsOfAlmawalehPOS.services.CategoryService;
import com.example.springsOfAlmawalehPOS.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/category")
@CrossOrigin
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/save-category")
    public CategoryModal saveCategory(@RequestBody CategoryModal categoryModal){
        return categoryService.saveCategory(categoryModal);
    }

    @GetMapping("/get-all-category")
    public List<CategoryModal> getAllCategory(){
        return categoryService.getAllCategory();
    }

    @GetMapping("/find-by-id-category/{id}")
    public List<CategoryModal> findByCategoryId(@PathVariable("id") Long id){
        return categoryService.findByCategoryId(id);
    }

    @PutMapping("/update-category/{id}")
    public CategoryModal getAllCategory(@PathVariable("id") Long Id,@RequestBody CategoryModal categoryModal){
        return categoryService.updateCategory(Id,categoryModal);
    }

    @DeleteMapping("/delete-category/{id}")
    public String deleteCategory(@PathVariable("id") Long id){
        return categoryService.deleteCategory(id);
    }

    @GetMapping("/get-category-by-outlet/{id}")
    public List<CategoryModal> getCategoryByOutlet(@PathVariable("id") Long id){
        return categoryService.getCategoryByOutlet(id);
    }
}
