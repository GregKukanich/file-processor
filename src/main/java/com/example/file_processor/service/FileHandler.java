package com.example.file_processor.service;

import com.example.file_processor.entity.File;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;

@Service
public class FileHandler {

    public File handleFileUpload(MultipartFile file ) {
        File uploadedFile = new File();
        uploadedFile.setFileName(file.getOriginalFilename());
        uploadedFile.setFileSize(file.getSize());
        uploadedFile.setFileType(file.getContentType());
        uploadedFile.setStatus("SUCCESS");
        uploadedFile.setCreatedAt(Instant.now().toString());

        return uploadedFile;
    }
}
