package com.example.springsOfAlmawalehPOS.modal;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Data
@Setter
@Getter
public class FileUploadModal {
    private MultipartFile file;
    private MultipartFile[] fileList;
}
