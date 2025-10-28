package com.rentflex.userservice.service.impl;

import com.rentflex.userservice.dto.UserRequest;
import com.rentflex.userservice.dto.UserResponse;
import com.rentflex.userservice.exception.ResourceNotFoundException;
import com.rentflex.userservice.model.Role;
import com.rentflex.userservice.model.Status;
import com.rentflex.userservice.model.User;
import com.rentflex.userservice.repository.UserRepository;
import com.rentflex.userservice.service.UserService;
import jakarta.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ServiceImpl implements UserService {

    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserResponse registerUser(UserRequest userRequest) {

        // Validate email
        if (userRepository.existsByEmail(userRequest.email())) {
            throw new RuntimeException("Email already registered");
        }
        User user = new User();
        user.setUserName(userRequest.userName());
        user.setEmail(userRequest.email());
        user.setPassword(passwordEncoder.encode(userRequest.password()));
        user.setRole(userRequest.role());
        user.setStatus(Status.ACTIVE);
        user.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        user.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        User savedUser = userRepository.save(user);
        return UserResponse.builder()
                .id(savedUser.getId())
                .userName(savedUser.getUserName())
                .email(savedUser.getEmail())
                .role(savedUser.getRole())
                .status(savedUser.getStatus())
                .message("User registered successfully")
                .build();
    }

    @Override
    public UserResponse getUserProfileById(Long userId) {
        User user =
                userRepository
                        .findById(userId)
                        .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return UserResponse.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .email(user.getEmail())
                .status(user.getStatus())
                .role(user.getRole())
                .build();
    }

    @Override
    public List<UserResponse> getAllUserProfile() {
        List<User> allUsers = userRepository.findAll();

        return allUsers.stream()
                .map(
                        user ->
                                UserResponse.builder()
                                        .id(user.getId())
                                        .userName(user.getUserName())
                                        .email(user.getEmail())
                                        .status(user.getStatus())
                                        .role(user.getRole())
                                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse updateUserProfile(Long userId, UserRequest userRequest) {
        User user =
                userRepository
                        .findById(userId)
                        .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        user.setUserName(userRequest.userName());
        user.setEmail(userRequest.email());
        user.setPassword(passwordEncoder.encode(userRequest.password()));
        user.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));

        User updatedUser = userRepository.save(user);

        return UserResponse.builder()
                .id(updatedUser.getId())
                .userName(updatedUser.getUserName())
                .email(updatedUser.getEmail())
                .role(updatedUser.getRole())
                .status(updatedUser.getStatus())
                .message("User updated successfully")
                .build();
    }

    @Override
    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found");
        }
        userRepository.deleteById(userId);
    }

    @Override
    public UserResponse updateUserRole(Long userId, Role role) {
        User user =
                userRepository
                        .findById(userId)
                        .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        user.setRole(role);
        User updatedUser = userRepository.save(user);

        return UserResponse.builder()
                .id(updatedUser.getId())
                .userName(updatedUser.getUserName())
                .email(updatedUser.getEmail())
                .role(updatedUser.getRole())
                .status(updatedUser.getStatus())
                .message("User role updated successfully. Now role is: " + updatedUser.getRole())
                .build();
    }

    @Override
    public UserResponse updateUserStatus(Long userId, Status status) {
        User user =
                userRepository
                        .findById(userId)
                        .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        user.setStatus(status);
        User updatedUser = userRepository.save(user);

        return UserResponse.builder()
                .id(updatedUser.getId())
                .userName(updatedUser.getUserName())
                .email(updatedUser.getEmail())
                .role(updatedUser.getRole())
                .status(updatedUser.getStatus())
                .message(
                        "User status updated successfully. Now status is: "
                                + updatedUser.getStatus())
                .build();
    }
}
