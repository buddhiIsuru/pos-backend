package com.example.springsOfAlmawalehPOS.services;

import com.example.springsOfAlmawalehPOS.entity.Category;
import com.example.springsOfAlmawalehPOS.entity.SubCategory;
import com.example.springsOfAlmawalehPOS.modal.SubCategoryModal;
import com.example.springsOfAlmawalehPOS.repositories.CategoryRepository;
import com.example.springsOfAlmawalehPOS.repositories.SubCategoryRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubCategoryService {
    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    public SubCategoryModal saveSubCategory(SubCategoryModal subCategoryModal){
        SubCategory subCategory= subCategoryRepository.save(modelMapper.map(subCategoryModal, SubCategory.class));
        return modelMapper.map(subCategory,SubCategoryModal.class);
    }

    public List<SubCategoryModal> getAllSubCategory(){
        List<SubCategory> subCategoryList= (List<SubCategory>) subCategoryRepository.findAll();
        return modelMapper.map(subCategoryList,new TypeToken<List<SubCategoryModal>>(){}.getType());
    }

    public SubCategoryModal updateSubCategory(Long id,SubCategoryModal subCategoryModal){
        Optional<SubCategory> alreadySubCategory=subCategoryRepository.findById(id);
        Category category=categoryRepository.findFirstById(subCategoryModal.getCategoryId());

        if(alreadySubCategory.isPresent()){
            SubCategory subCategory=alreadySubCategory.get();
            subCategory.setId(id);
            subCategory.setName(subCategoryModal.getName());
            subCategory.setCategory(category);
            return modelMapper.map(subCategoryRepository.save(subCategory),SubCategoryModal.class);
        }
        return null;
    }

    public String deleteSubCategory(Long id) {
        subCategoryRepository.deleteById(id);
        return "";
    }

    public List<SubCategoryModal> findBySubCategoryId(Long id) {
        Optional<SubCategory> subCategoryList= subCategoryRepository.findById(id);
        return modelMapper.map(subCategoryList,new TypeToken<List<SubCategoryModal>>(){}.getType());
    }
}
