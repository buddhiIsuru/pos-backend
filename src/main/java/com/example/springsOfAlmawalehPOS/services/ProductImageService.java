package com.example.springsOfAlmawalehPOS.services;

import com.example.springsOfAlmawalehPOS.entity.ProductImage;
import com.example.springsOfAlmawalehPOS.repositories.ProductImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import util.ImageUtils;

import java.io.IOException;
import java.util.Optional;

@Service
public class ProductImageService {

    @Autowired
    private ProductImageRepository imageRepo;

    public Long uploadImage(MultipartFile file) throws IOException {
        ProductImage pImage = new ProductImage();
        pImage.setName(file.getOriginalFilename());
        pImage.setType(file.getContentType());
        pImage.setImageData(ImageUtils.compressImage(file.getBytes()));
        return imageRepo.save(pImage).getId();
    }

    public byte[] downloadImage(String fileName){
        Optional<ProductImage> imageData = imageRepo.findByName(fileName);
        return ImageUtils.decompressImage(imageData.get().getImageData());
    }

    public byte[] getImageInId(Long id){
        Optional<ProductImage> imageData = imageRepo.findById(id);
        return ImageUtils.decompressImage(imageData.get().getImageData());
    }
}
