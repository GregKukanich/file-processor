package com.example.file_processor.controller;

import com.example.file_processor.dto.FileUploadResponse;
import com.example.file_processor.entity.File;
import com.example.file_processor.service.FileHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/file")
public class FileController {

    private final FileHandler fileHandler;

    public FileController(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    @PostMapping
    public ResponseEntity<FileUploadResponse> upload(@RequestParam("file") MultipartFile file) {
        if (!validate(file)) {
            return ResponseEntity.badRequest().body(new FileUploadResponse("Invalid file"));
        }
        File uploadedFile = fileHandler.handleFileUpload(file);
        return ResponseEntity.ok(new FileUploadResponse(uploadedFile));
    }

    private Boolean validate(MultipartFile file) {
        return !file.isEmpty();
    }
}
