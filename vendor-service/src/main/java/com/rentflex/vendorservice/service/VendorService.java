package com.rentflex.vendorservice.service;

import com.rentflex.vendorservice.dto.VendorRequest;
import com.rentflex.vendorservice.dto.VendorResponse;
import com.rentflex.vendorservice.model.Status;
import java.util.List;

public interface VendorService {

    VendorResponse createVendor(VendorRequest request);

    VendorResponse getVendorById(Long vendorId);

    VendorResponse updateVendor(Long vendorId, VendorRequest request);

    List<VendorResponse> getAllVendors();

    VendorResponse updateVendorStatus(Long vendorId, Status status);

    //    VendorDashboardDTO getVendorDashboard(Long vendorId);
    void deleteVendor(Long vendorId);

    boolean existsByUserId(Long userId);

    VendorResponse findByUserId(Long userId);
}
