package com.rentflex.paymentservice.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment_audit")
public class PaymentAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String paymentId;
    private String event;
    private LocalDateTime eventTime;
    private String message;
}
