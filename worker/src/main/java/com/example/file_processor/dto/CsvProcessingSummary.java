package com.example.file_processor.dto;

public record CsvProcessingSummary(
        int totalRows,
        int validRows,
        int invalidRows,
        int purchaseCount,
        int refundCount,
        double totalPurchaseAmount,
        double totalRefundAmount,
        double netAmount
) {
}
