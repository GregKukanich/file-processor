package com.example.file_processor.service;

import com.example.file_processor.dto.CsvProcessingSummary;
import com.example.file_processor.entity.File;
import com.example.file_processor.repository.FileRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Optional;

@Service
public class FileHandler {

    private final FileRepository fileRepository;

    public FileHandler(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public File handleFileUpload(MultipartFile file ) throws IOException {
        File uploadedFile = new File();
        uploadedFile.setFileName(file.getOriginalFilename());
        uploadedFile.setFileSize(file.getSize());
        uploadedFile.setFileType(file.getContentType());
        uploadedFile.setStatus("COMPLETED");

        CsvProcessingSummary summary = parseFile(file);
        uploadedFile.setTotalRows(summary.totalRows());
        uploadedFile.setValidRows(summary.validRows());
        uploadedFile.setInvalidRows(summary.invalidRows());

        uploadedFile.setPurchaseCount(summary.purchaseCount());
        uploadedFile.setTotalPurchaseAmount(summary.totalPurchaseAmount());

        uploadedFile.setRefundCount(summary.refundCount());
        uploadedFile.setTotalRefundAmount(summary.totalRefundAmount());

        uploadedFile.setNetAmount(summary.netAmount());

        fileRepository.save(uploadedFile);

        return uploadedFile;
    }

    public CsvProcessingSummary parseFile(MultipartFile file) throws IOException {
        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setHeader()
                .setSkipHeaderRecord(true)
                .get();

        int totalRows = 0;
        int validRows = 0;
        int invalidRows = 0;
        int purchaseCount = 0;
        double totalPurchaseAmount = 0.0;
        int refundCount = 0;
        double totalRefundAmount = 0.0;
        double netAmount = 0.0;

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
             CSVParser csvParser = CSVParser.parse(br, format)) {

            for (CSVRecord record : csvParser) {
                if (validRecord(record)) {
                    validRows++;
                    if (record.get("type").equals("PURCHASE")) {
                        purchaseCount++;
                        double amount = Double.parseDouble(record.get("amount"));
                        totalPurchaseAmount += amount;
                        netAmount += amount;
                    } else if (record.get("type").equals("REFUND")) {
                        refundCount++;
                        double amount = Double.parseDouble(record.get("amount"));
                        totalRefundAmount += amount;
                        netAmount -= amount;
                    }
                } else {
                    invalidRows++;
                }

                totalRows++;
            }
        }

        return new CsvProcessingSummary(totalRows, validRows, invalidRows,
                purchaseCount, refundCount, totalPurchaseAmount, totalRefundAmount, netAmount);
    }

    public boolean validRecord(CSVRecord record) {
        if (record == null) {
            return false;
        }

        //transaction_id,customer_id,amount,type,timestamp
        String transactionId = record.get("transaction_id");
        String customerId = record.get("customer_id");
        String amount = record.get("amount");
        String type = record.get("type");
        String timestamp = record.get("timestamp");

        if (transactionId.isBlank() || customerId.isBlank() || amount.isBlank()
                || type.isBlank() || timestamp.isBlank()) {
            return false;
        }

        if (!type.equals("PURCHASE") && !type.equals("REFUND")) {
            return false;
        }

        try {
            double parsedAmount = Double.parseDouble(amount);
            if (parsedAmount < 0.0) {
                return false;
            }
            LocalDateTime.parse(timestamp);
        } catch (NumberFormatException | DateTimeParseException e) {
            return false;
        }

        return true;
    }


    public Optional<File> getFileStatus(Long id) {
        return fileRepository.findById(id);
    }
}
