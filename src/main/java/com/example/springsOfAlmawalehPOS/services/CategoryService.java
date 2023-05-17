package com.example.springsOfAlmawalehPOS.services;

import com.example.springsOfAlmawalehPOS.entity.Category;
import com.example.springsOfAlmawalehPOS.entity.Customer;
import com.example.springsOfAlmawalehPOS.entity.Outlet;
import com.example.springsOfAlmawalehPOS.modal.CategoryModal;
import com.example.springsOfAlmawalehPOS.modal.CustomerModal;
import com.example.springsOfAlmawalehPOS.repositories.CategoryRepository;
import com.example.springsOfAlmawalehPOS.repositories.CustomerRepository;
import com.example.springsOfAlmawalehPOS.repositories.OutletRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private OutletRepository outletRepository;

    @Autowired
    private ModelMapper modelMapper;

    public CategoryModal saveCategory(CategoryModal categoryModal){
        Category category=new Category();
        Outlet outlet=outletRepository.findFirstById(categoryModal.getOutletId());
        System.out.println(categoryModal.getId());
        category.setId(categoryModal.getId());
        category.setName(categoryModal.getName());
        category.setOutlet(outlet);
        return modelMapper.map(categoryRepository.save(category),CategoryModal.class);
    }

    public List<CategoryModal> getAllCategory(){
        List<Category> categoryList= (List<Category>) categoryRepository.findAll();
        return modelMapper.map(categoryList,new TypeToken<List<CategoryModal>>(){}.getType());
    }

    public CategoryModal updateCategory(Long id,CategoryModal categoryModal){
        Optional<Category> alreadyCategory=categoryRepository.findById(id);

        if(alreadyCategory.isPresent()){
            Category category=alreadyCategory.get();
            category.setId(id);
            category.setName(categoryModal.getName());
            return modelMapper.map(categoryRepository.save(category),CategoryModal.class);
        }
        return null;
    }

    public String deleteCategory(Long id) {
        categoryRepository.deleteById(id);
        return "";
    }

    public List<CategoryModal> findByCategoryId(Long id) {
        Optional<Category> categoryList = categoryRepository.findById(id);
        return modelMapper.map(categoryList, new TypeToken<List<CategoryModal>>() {
        }.getType());
    }

    public List<CategoryModal> findByCategoryOutlet(Long id) {
        List<Category> categoryList = categoryRepository.findAllByOutlet(outletRepository.findFirstById(id));
        return modelMapper.map(categoryList, new TypeToken<List<CategoryModal>>() {
        }.getType());
    }

    public List<CategoryModal> getCategoryByOutlet(Long id) {
        List<Category> categoryList = categoryRepository.findAllByOutlet(outletRepository.findFirstById(id));
        return modelMapper.map(categoryList, new TypeToken<List<CategoryModal>>() {
        }.getType());
    }
}
