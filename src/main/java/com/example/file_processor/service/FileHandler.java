package com.example.file_processor.service;

import com.example.file_processor.entity.File;
import com.example.file_processor.repository.FileRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileHandler {

    private final FileRepository fileRepository;

    public FileHandler(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public File handleFileUpload(MultipartFile file ) {
        File uploadedFile = new File();
        uploadedFile.setFileName(file.getOriginalFilename());
        uploadedFile.setFileSize(file.getSize());
        uploadedFile.setFileType(file.getContentType());
        uploadedFile.setStatus("SUCCESS");
        fileRepository.save(uploadedFile);

        return uploadedFile;
    }
}
