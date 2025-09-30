package com.rentflex.userservice.service.impl;

import com.rentflex.userservice.dto.Request;
import com.rentflex.userservice.dto.Response;
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
    public Response registerUser(Request request) {

        // Validate email
        if (userRepository.existsByEmail(request.email())) {
            throw new RuntimeException("Email already registered");
        }
        User user = new User();
        user.setUserName(request.userName());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(request.role());
        user.setStatus(Status.ACTIVE);
        user.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        user.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        User savedUser = userRepository.save(user);
        return Response.builder()
                .id(savedUser.getId())
                .userName(savedUser.getUserName())
                .email(savedUser.getEmail())
                .role(savedUser.getRole())
                .status(savedUser.getStatus())
                .message("User registered successfully")
                .build();
    }

    @Override
    public Response getUserProfileById(Long userId) {
        User user =
                userRepository
                        .findById(userId)
                        .orElseThrow(() -> new RuntimeException("User not found"));

        return Response.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .email(user.getEmail())
                .status(user.getStatus())
                .role(user.getRole())
                .build();
    }

    @Override
    public List<Response> getAllUserProfile() {
        List<User> allUsers = userRepository.findAll();

        return allUsers.stream()
                .map(
                        user ->
                                Response.builder()
                                        .id(user.getId())
                                        .userName(user.getUserName())
                                        .email(user.getEmail())
                                        .status(user.getStatus())
                                        .role(user.getRole())
                                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public Response updateUserProfile(Long userId, Request request) {
        User user =
                userRepository
                        .findById(userId)
                        .orElseThrow(() -> new RuntimeException("User not found"));

        user.setUserName(request.userName());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));

        User updatedUser = userRepository.save(user);

        return Response.builder()
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
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(userId);
    }

    @Override
    public Response updateUserRole(Long userId, Role role) {
        User user =
                userRepository
                        .findById(userId)
                        .orElseThrow(() -> new RuntimeException("User not found"));

        user.setRole(role);
        User updatedUser = userRepository.save(user);

        return Response.builder()
                .id(updatedUser.getId())
                .userName(updatedUser.getUserName())
                .email(updatedUser.getEmail())
                .role(updatedUser.getRole())
                .status(updatedUser.getStatus())
                .message("User role updated successfully. Now role is: " + updatedUser.getRole())
                .build();
    }

    @Override
    public Response updateUserStatus(Long userId, Status status) {
        User user =
                userRepository
                        .findById(userId)
                        .orElseThrow(() -> new RuntimeException("User not found"));

        user.setStatus(status);
        User updatedUser = userRepository.save(user);

        return Response.builder()
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
