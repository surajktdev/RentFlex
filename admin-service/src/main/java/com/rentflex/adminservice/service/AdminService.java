package com.rentflex.adminservice.service;

import com.rentflex.adminservice.dto.AdminUserResponse;
import com.rentflex.adminservice.dto.AdminVendorResponse;
import com.rentflex.adminservice.dto.ManageUserRequest;
import java.util.List;

public interface AdminService {

    List<AdminUserResponse> getAllUsers();

    AdminUserResponse createUser(ManageUserRequest request);

    AdminUserResponse updateUser(Long id, ManageUserRequest request);

    void deleteUser(Long id);

    List<AdminVendorResponse> getAllVendors();
}
