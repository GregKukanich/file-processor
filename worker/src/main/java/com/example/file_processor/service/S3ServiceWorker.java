package com.example.file_processor.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;

import java.io.InputStream;

@Service
public class S3ServiceWorker {
    private final S3Client s3Client;
    private final String bucketName;

    public S3ServiceWorker(S3Client s3Client, @Value("${aws.s3.bucket-name}") String bucketName) {
        this.s3Client = s3Client;
        this.bucketName = bucketName;
    }

    public InputStream downloadFile(String s3Key) {
        return s3Client.getObject(GetObjectRequest.builder()
                .bucket(bucketName)
                .key(s3Key)
                .build());
    }
}
