package com.example.file_processor.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Getter
@Setter
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public Long fileSize;
    public String fileType;
    public String s3Key;
    public String fileName;
    @CreationTimestamp
    public String createdAt;
    public String status;

    //summary fields
    public Integer totalRows;
    public Integer validRows;
    public Integer invalidRows;

    public Integer purchaseCount;
    public Double totalPurchaseAmount;

    public Integer refundCount;
    public Double totalRefundAmount;

    public Double netAmount;

}
