package com.rentflex.adminservice.controller;

import com.rentflex.adminservice.dto.AdminUserResponse;
import com.rentflex.adminservice.dto.AdminVendorResponse;
import com.rentflex.adminservice.dto.ManageUserRequest;
import com.rentflex.adminservice.service.AdminService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/users")
    public ResponseEntity<List<AdminUserResponse>> getAllUsers() {
        return ResponseEntity.ok(adminService.getAllUsers());
    }

    @PostMapping("/users")
    public ResponseEntity<AdminUserResponse> createUser(@RequestBody ManageUserRequest request) {
        return ResponseEntity.ok(adminService.createUser(request));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<AdminUserResponse> updateUser(
            @PathVariable Long id, @RequestBody ManageUserRequest request) {
        return ResponseEntity.ok(adminService.updateUser(id, request));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        adminService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/vendors")
    public ResponseEntity<List<AdminVendorResponse>> getAllVendors() {
        return ResponseEntity.ok(adminService.getAllVendors());
    }
}
