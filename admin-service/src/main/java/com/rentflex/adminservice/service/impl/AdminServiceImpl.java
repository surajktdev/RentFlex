package com.rentflex.adminservice.service.impl;

import com.rentflex.adminservice.dto.AdminUserResponse;
import com.rentflex.adminservice.dto.AdminVendorResponse;
import com.rentflex.adminservice.dto.ManageUserRequest;
import com.rentflex.adminservice.feign.UserClient;
import com.rentflex.adminservice.feign.VendorClient;
import com.rentflex.adminservice.service.AdminService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserClient userClient;
    private final VendorClient vendorClient;

    @Override
    public List<AdminUserResponse> getAllUsers() {
        return List.of();
    }

    @Override
    public AdminUserResponse createUser(ManageUserRequest request) {
        return null;
    }

    @Override
    public AdminUserResponse updateUser(Long id, ManageUserRequest request) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {}

    @Override
    public List<AdminVendorResponse> getAllVendors() {
        return List.of();
    }
}
