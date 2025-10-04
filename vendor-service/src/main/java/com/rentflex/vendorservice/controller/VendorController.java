package com.rentflex.vendorservice.controller;

import com.rentflex.vendorservice.dto.VendorRequest;
import com.rentflex.vendorservice.dto.VendorResponse;
import com.rentflex.vendorservice.service.VendorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

        return null;
    }
}
