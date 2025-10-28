package com.rentflex.paymentservice.dto;

public record RefundRequestDTO(String transactionId, Double refundAmount, String reason) {}
