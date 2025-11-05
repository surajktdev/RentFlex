package com.rentflex.inventoryservice.client;

public record VendorRequest(
    Long userId,
    String businessName,
    String phoneNumber,
    String address,
    String gstNumber,
    Kyc_Status kycStatus,
    Status status) {}
