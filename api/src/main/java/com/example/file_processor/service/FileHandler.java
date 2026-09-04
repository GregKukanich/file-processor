package com.example.file_processor.service;

import com.example.file_processor.entity.File;
import com.example.file_processor.repository.FileRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class FileHandler {

    private final FileRepository fileRepository;
    private final S3Service s3Service;
    private final SqsProducerService sqsProducerService;

    public FileHandler(FileRepository fileRepository, S3Service s3Service, SqsProducerService sqsProducerService) {
        this.fileRepository = fileRepository;
        this.s3Service = s3Service;
        this.sqsProducerService = sqsProducerService;
    }

    public File handleFileUpload(MultipartFile file ) throws IOException {
        File uploadedFile = new File();
        uploadedFile.setFileName(file.getOriginalFilename());
        uploadedFile.setFileSize(file.getSize());
        uploadedFile.setFileType(file.getContentType());
        uploadedFile.setStatus("PENDING");

        String key = s3Service.uploadFile(file);
        uploadedFile.setS3Key(key);

        fileRepository.save(uploadedFile);
        sqsProducerService.sendMessage(uploadedFile.getId().toString());

        return uploadedFile;
    }

    public Optional<File> getFileStatus(Long id) {
        return fileRepository.findById(id);
    }
}
