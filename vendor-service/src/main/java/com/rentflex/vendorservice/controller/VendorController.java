package com.rentflex.vendorservice.controller;

import com.rentflex.vendorservice.dto.VendorRequest;
import com.rentflex.vendorservice.dto.VendorResponse;
import com.rentflex.vendorservice.model.Status;
import com.rentflex.vendorservice.service.VendorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vendor")
@Tag(
        name = "Vendor Operations",
        description = "Endpoints for handling vendor-related functionalities")
public class VendorController {

    @Autowired private VendorService vendorService;

    @PostMapping(value = "/register")
    @Operation(summary = "Register New Vendor")
    public ResponseEntity<VendorResponse> register(@RequestBody VendorRequest request) {
        VendorResponse vendor = vendorService.createVendor(request);
        return new ResponseEntity<>(vendor, HttpStatus.CREATED);
    }

    @GetMapping("/{vendorId}")
    @Operation(summary = "Get vendor details by their Id")
    public ResponseEntity<VendorResponse> getVendorById(@PathVariable Long vendorId) {
        VendorResponse vendorDetailsById = vendorService.getVendorById(vendorId);
        return new ResponseEntity<>(vendorDetailsById, HttpStatus.FOUND);
    }

    @PutMapping("/{vendorId}")
    @Operation(summary = "Update vendor details by their Id")
    public ResponseEntity<VendorResponse> updateVendor(
            @PathVariable Long vendorId, @RequestBody VendorRequest request) {
        VendorResponse vendorResponse = vendorService.updateVendor(vendorId, request);
        return new ResponseEntity<>(vendorResponse, HttpStatus.OK);
    }

    @GetMapping("/")
    @Operation(summary = "Get all vendors details")
    public ResponseEntity<List<VendorResponse>> getAllVendor() {
        List<VendorResponse> allVendors = vendorService.getAllVendors();
        return new ResponseEntity<>(allVendors, HttpStatus.FOUND);
    }

    @PatchMapping("/{vendorId}/status")
    @Operation(summary = "Update vendor status by their Id")
    public ResponseEntity<VendorResponse> updateVendorStatus(
            @PathVariable Long vendorId, @RequestBody Status status) {
        VendorResponse vendorResponse = vendorService.updateVendorStatus(vendorId, status);
        return new ResponseEntity<>(vendorResponse, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Get vendor details by user Id")
    public ResponseEntity<VendorResponse> findByUserId(@PathVariable Long userId) {
        VendorResponse byUserId = vendorService.findByUserId(userId);
        return new ResponseEntity<>(byUserId, HttpStatus.FOUND);
    }

    @DeleteMapping("/{vendorId}")
    @Operation(summary = "Delete vendor details by their Id")
    public ResponseEntity deleteVendorById(@PathVariable Long vendorId) {
        vendorService.deleteVendor(vendorId);
        return ResponseEntity.ok("Vendor deleted successfully");
    }
}
