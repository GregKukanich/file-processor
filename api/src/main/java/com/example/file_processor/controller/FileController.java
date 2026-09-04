package com.example.file_processor.controller;

import com.example.file_processor.dto.FileStatusResponse;
import com.example.file_processor.dto.FileUploadResponse;
import com.example.file_processor.entity.File;
import com.example.file_processor.service.FileHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/file")
public class FileController {

    private final FileHandler fileHandler;

    public FileController(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    @PostMapping
    public ResponseEntity<FileUploadResponse> upload(@RequestParam("file") MultipartFile file) throws IOException {
        if (!validate(file)) {
            return ResponseEntity.badRequest().body(new FileUploadResponse("Invalid file"));
        }
        File uploadedFile = fileHandler.handleFileUpload(file);
        return ResponseEntity.ok(new FileUploadResponse(uploadedFile));
    }

    private Boolean validate(MultipartFile file) {
        return !file.isEmpty();
    }

    @GetMapping
    public ResponseEntity<FileStatusResponse> getFileStatus(@RequestParam("id") Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().body(new FileStatusResponse("Invalid id"));
        }

        Optional<File> file = fileHandler.getFileStatus(id);
        if (file.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new FileStatusResponse(file.get()));
    }
}
