package com.example.springsOfAlmawalehPOS.services;

import com.example.springsOfAlmawalehPOS.entity.Category;
import com.example.springsOfAlmawalehPOS.entity.Company;
import com.example.springsOfAlmawalehPOS.entity.Outlet;
import com.example.springsOfAlmawalehPOS.modal.CategoryModal;
import com.example.springsOfAlmawalehPOS.modal.OutletModal;
import com.example.springsOfAlmawalehPOS.repositories.CategoryRepository;
import com.example.springsOfAlmawalehPOS.repositories.CompanyRepository;
import com.example.springsOfAlmawalehPOS.repositories.OutletRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OutletService {
    @Autowired
    private OutletRepository outletRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ResponseEntity<OutletModal> saveOutlet(OutletModal outletModal){
        Outlet outlet= new Outlet();
        Company company=companyRepository.findFirstById(outletModal.getCompanyId());
        outlet.setOutletName(outletModal.getOutletName());
        outlet.setAddress(outletModal.getAddress());
        outlet.setPhoneNo(outletModal.getPhoneNo());
        outlet.setLogoId(outletModal.getLogoId());
        outlet.setCompany(company);

        OutletModal outletModal1=modelMapper.map(outletRepository.save(outlet),OutletModal.class);
        return new ResponseEntity<>(outletModal1, HttpStatus.OK);
    }

    public List<OutletModal> getAllOutlet(){
        List<Outlet> outletList= (List<Outlet>) outletRepository.findAll();
        return modelMapper.map(outletList,new TypeToken<List<OutletModal>>(){}.getType());
    }

    public CategoryModal updateOutlet(Long id,CategoryModal categoryModal){
        return null;
    }

    public String deleteOutlet(Long id) {
        outletRepository.deleteById(id);
        return "";
    }

    public List<CategoryModal> findByOutletId(Long id) {
        Optional<Outlet> outletList= outletRepository.findById(id);
        return modelMapper.map(outletList,new TypeToken<List<OutletModal>>(){}.getType());
    }
}
