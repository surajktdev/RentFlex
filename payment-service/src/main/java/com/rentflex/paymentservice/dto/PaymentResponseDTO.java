package com.rentflex.paymentservice.dto;

import com.rentflex.paymentservice.model.PaymentMethod;
import com.rentflex.paymentservice.model.PaymentStatus;
import java.time.LocalDateTime;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponseDTO {

    private Long paymentId;
    private String bookingId;
    private Long userId;
    private Long vendorId;
    private Double amount;
    private String currency;
    private PaymentMethod method;
    private PaymentStatus status;
    private String transactionId;
    private String gatewayName;
    private LocalDateTime paymentDate;
    private String message;
}
