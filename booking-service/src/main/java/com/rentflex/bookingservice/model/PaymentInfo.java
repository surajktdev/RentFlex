package com.rentflex.bookingservice.model;

import jakarta.persistence.*;

@Entity
@Table(name = "payments")
public class PaymentInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    private Long bookingId; // Reference to Booking
    private Double amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
}
