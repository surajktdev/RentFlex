package com.rentflex.paymentservice.dto;

import com.rentflex.paymentservice.model.PaymentMethod;

public record PaymentRequestDTO(
        String bookingId,
        Long userId,
        Long vendorId,
        Double amount,
        String currency,
        PaymentMethod method,
        String gatewayName) {}
