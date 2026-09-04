package com.example.file_processor.dto;

import com.example.file_processor.entity.File;
import lombok.Data;

@Data
public class FileStatusResponse {

    private String fileName;
    private long fileSize;
    private String fileType;
    private String createdAt;
    private String status;

    //summary fields
    private Integer totalRows;
    private Integer validRows;
    private Integer invalidRows;

    private Integer purchaseCount;
    private Double totalPurchaseAmount;

    private Integer refundCount;
    private Double totalRefundAmount;

    private Double netAmount;

    public FileStatusResponse(File file) {
        this.fileName = file.getFileName();
        this.fileSize = file.getFileSize();
        this.fileType = file.getFileType();
        this.createdAt = file.getCreatedAt();
        this.status = file.getStatus();

        this.totalRows = file.getTotalRows();
        this.validRows = file.getValidRows();
        this.invalidRows = file.getInvalidRows();

        this.purchaseCount = file.getPurchaseCount();
        this.totalPurchaseAmount = file.getTotalPurchaseAmount();

        this.refundCount = file.getRefundCount();
        this.totalRefundAmount = file.getTotalRefundAmount();

        this.netAmount = file.getNetAmount();
    }

    public FileStatusResponse(String status) {
        this.status = status;
    }

}
