package com.rentflex.vendorservice.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Entity
@Table
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long venderId;

    private String businessName;
    private String email;
    private String phoneNumber;
    private String address;
    private String gstNumber;

    @Enumerated(EnumType.STRING)
    private Kyc_Status kycStatus = Kyc_Status.PENDING;

    @Enumerated(EnumType.STRING)
    private Status status = Status.INACTIVE;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();
}
