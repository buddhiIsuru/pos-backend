package com.example.springsOfAlmawalehPOS.services;

import com.example.springsOfAlmawalehPOS.entity.Category;
import com.example.springsOfAlmawalehPOS.entity.Company;
import com.example.springsOfAlmawalehPOS.entity.Outlet;
import com.example.springsOfAlmawalehPOS.modal.CategoryModal;
import com.example.springsOfAlmawalehPOS.modal.CompanyModal;
import com.example.springsOfAlmawalehPOS.repositories.CategoryRepository;
import com.example.springsOfAlmawalehPOS.repositories.CompanyRepository;
import com.example.springsOfAlmawalehPOS.repositories.OutletRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private OutletRepository outletRepository;

    @Autowired
    private ModelMapper modelMapper;

    public CompanyModal saveCompany(CompanyModal companyModal){
        Company company=new Company();
        company.setName(companyModal.getName());
        return modelMapper.map(companyRepository.save(company),CompanyModal.class);
    }

    public List<CompanyModal> getCompany() {
        Iterable<Company> companyList= companyRepository.findAll();
        return modelMapper.map(companyList, new TypeToken<List<CompanyModal>>() {
        }.getType());
    }
}
