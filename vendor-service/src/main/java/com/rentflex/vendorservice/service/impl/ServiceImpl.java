package com.rentflex.vendorservice.service.impl;

import com.rentflex.vendorservice.client.UserClient;
import com.rentflex.vendorservice.client.UserResponse;
import com.rentflex.vendorservice.dto.VendorRequest;
import com.rentflex.vendorservice.dto.VendorResponse;
import com.rentflex.vendorservice.exception.EmailAlreadyExistsException;
import com.rentflex.vendorservice.exception.ResourceNotFoundException;
import com.rentflex.vendorservice.model.Status;
import com.rentflex.vendorservice.model.Vendor;
import com.rentflex.vendorservice.repository.VendorRepo;
import com.rentflex.vendorservice.service.VendorService;
import feign.FeignException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceImpl implements VendorService {

    @Autowired private VendorRepo vendorRepo;

    @Autowired private UserClient userClient;

    @Override
    public VendorResponse createVendor(VendorRequest request) {
        UserResponse user = null;
        try {
            user = userClient.getUserById(request.userId());
        } catch (FeignException.NotFound ex) {
            throw new ResourceNotFoundException("User not found for vendor onboarding");
        }

        if (vendorRepo.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyExistsException("Email already registered");
        }

        Vendor vendor = new Vendor();
        vendor.setBusinessName(request.businessName());
        vendor.setEmail(user.getEmail());
        vendor.setPhoneNumber(request.phoneNumber());
        vendor.setAddress(request.address());
        vendor.setGstNumber(request.gstNumber());
        vendor.setKycStatus(request.kycStatus());
        vendor.setStatus(request.status());
        vendor.setCreatedAt(LocalDateTime.now());
        vendor.setUpdatedAt(LocalDateTime.now());
        Vendor savedVendorDetails = vendorRepo.save(vendor);
        return VendorResponse.builder()
                .vendorId(savedVendorDetails.getVendorId())
                .userId(savedVendorDetails.getUserId())
                .businessName(savedVendorDetails.getBusinessName())
                .message("Vendor Details added successfully.")
                .build();
    }

    @Override
    public VendorResponse getVendorById(Long vendorId) {
        Vendor savedVendorDetails =
                vendorRepo
                        .findById(vendorId)
                        .orElseThrow(() -> new RuntimeException("Vendor not found"));

        return VendorResponse.builder()
                .businessName(savedVendorDetails.getBusinessName())
                .email(savedVendorDetails.getEmail())
                .phoneNumber(savedVendorDetails.getPhoneNumber())
                .address(savedVendorDetails.getAddress())
                .createdAt(savedVendorDetails.getCreatedAt())
                .updatedAt(savedVendorDetails.getUpdatedAt())
                .build();
    }

    @Override
    public VendorResponse updateVendor(Long vendorId, VendorRequest request) {
        Vendor vendorDetails =
                vendorRepo
                        .findById(vendorId)
                        .orElseThrow(() -> new RuntimeException("Vendor not found"));

        vendorDetails.setBusinessName(request.businessName());
        vendorDetails.setAddress(request.address());
        vendorDetails.setStatus(request.status());

        Vendor savedVendorDetails = vendorRepo.save(vendorDetails);
        return VendorResponse.builder()
                .businessName(savedVendorDetails.getBusinessName())
                .message("Vendor details updated successfully.")
                .build();
    }

    @Override
    public List<VendorResponse> getAllVendors() {

        List<Vendor> savedVendors = vendorRepo.findAll();
        return savedVendors.stream()
                .map(
                        savedVendorDetails ->
                                VendorResponse.builder()
                                        .businessName(savedVendorDetails.getBusinessName())
                                        .email(savedVendorDetails.getEmail())
                                        .phoneNumber(savedVendorDetails.getPhoneNumber())
                                        .address(savedVendorDetails.getAddress())
                                        .createdAt(savedVendorDetails.getCreatedAt())
                                        .updatedAt(savedVendorDetails.getUpdatedAt())
                                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public VendorResponse updateVendorStatus(Long vendorId, Status status) {
        Vendor vendorDetails =
                vendorRepo
                        .findById(vendorId)
                        .orElseThrow(() -> new RuntimeException("Vendor not found"));

        vendorDetails.setStatus(status);
        Vendor savedVendorDetails = vendorRepo.save(vendorDetails);
        return VendorResponse.builder()
                .status(savedVendorDetails.getStatus())
                .message("Vendor Status updated successfully.")
                .build();
    }

    @Override
    public void deleteVendor(Long vendorId) {
        Vendor savedVendorDetails =
                vendorRepo
                        .findById(vendorId)
                        .orElseThrow(() -> new RuntimeException("Vendor not found"));

        vendorRepo.deleteById(savedVendorDetails.getVendorId());
    }

    @Override
    public boolean existsByUserId(Long userId) {
        return vendorRepo.existsById(userId);
    }

    @Override
    public VendorResponse findByUserId(Long userId) {
        Vendor savedVendorDetails =
                vendorRepo
                        .findByUserId(userId)
                        .orElseThrow(() -> new RuntimeException("Vendor Details not found"));
        return VendorResponse.builder()
                .businessName(savedVendorDetails.getBusinessName())
                .email(savedVendorDetails.getEmail())
                .phoneNumber(savedVendorDetails.getPhoneNumber())
                .address(savedVendorDetails.getAddress())
                .createdAt(savedVendorDetails.getCreatedAt())
                .updatedAt(savedVendorDetails.getUpdatedAt())
                .build();
    }
}
