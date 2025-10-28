package com.rentflex.paymentservice.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Data;

@Entity
@Table(name = "payments")
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    private String bookingId;
    private Long userId;
    private Long vendorId;
    private Double amount;
    private String currency;
    private PaymentStatus status;
    private PaymentMethod method;
    private LocalDateTime paymentDate;
    private String transactionId;
    private String gatewayName;
}
