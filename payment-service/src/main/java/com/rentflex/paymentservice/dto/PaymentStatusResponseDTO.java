package com.rentflex.paymentservice.dto;

import com.rentflex.paymentservice.model.PaymentStatus;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PaymentStatusResponseDTO {
    private String transactionId;
    private PaymentStatus status;
    private String message;

    public PaymentStatusResponseDTO(String transactionId, PaymentStatus status, String message) {
        this.transactionId = transactionId;
        this.status = status;
        this.message = message;
    }
}
