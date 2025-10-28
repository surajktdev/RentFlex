package com.rentflex.userservice.service;

import com.rentflex.userservice.dto.UserRequest;
import com.rentflex.userservice.dto.UserResponse;
import com.rentflex.userservice.model.Role;
import com.rentflex.userservice.model.Status;
import java.util.List;

public interface UserService {

    UserResponse registerUser(UserRequest userRequest);

    UserResponse getUserProfileById(Long userId);

    List<UserResponse> getAllUserProfile();

    UserResponse updateUserProfile(Long userId, UserRequest userRequest);

    void deleteUser(Long userId);

    UserResponse updateUserRole(Long userId, Role role);

    UserResponse updateUserStatus(Long userId, Status status);
}
