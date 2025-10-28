package com.rentflex.paymentservice.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Data;

@Entity
@Table(name = "refund")
@Data
public class Refund {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long refundId;

    private String transactionId;
    private Double refundAmount;
    private LocalDateTime refundDate;
    private RefundStatus refundStatus;
    private String reason;
}
