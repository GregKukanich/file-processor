package com.example.file_processor.dto;

import com.example.file_processor.entity.File;
import lombok.Data;

@Data
public class FileUploadResponse {
    private String fileName;
    private long fileSize;
    private String fileType;
    private String createdAt;
    private String status;

    public FileUploadResponse(File file) {
        this.fileName = file.getFileName();
        this.fileSize = file.getFileSize();
        this.fileType = file.getFileType();
        this.createdAt = file.getCreatedAt();
        this.status = file.getStatus();
    }

    public FileUploadResponse(String status) {
        this.status = status;
    }


}
