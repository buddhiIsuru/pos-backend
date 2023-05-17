package com.example.springsOfAlmawalehPOS.controlles;


import java.io.IOException;

import com.example.springsOfAlmawalehPOS.entity.ProductImage;
import com.example.springsOfAlmawalehPOS.services.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "api/file")
@CrossOrigin
public class ProductImageController {

    @Autowired
    private ProductImageService productImageService;

    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping("/upload")
    public Long uploadImage(@RequestParam("productImage")MultipartFile file) throws IOException{
        return productImageService.uploadImage(file);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable String fileName) {
        byte[] image = productImageService.downloadImage(fileName);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(image);
    }

    @GetMapping("/get-id/{id}")
    public ResponseEntity<byte[]> getImageInId(@PathVariable Long id) {
        byte[] image = productImageService.getImageInId(id);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(image);
    }
}
