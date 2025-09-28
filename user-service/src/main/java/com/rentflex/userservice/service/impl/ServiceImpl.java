package com.rentflex.userservice.service.impl;

import com.rentflex.userservice.dto.Request;
import com.rentflex.userservice.dto.Response;
import com.rentflex.userservice.model.Status;
import com.rentflex.userservice.model.User;
import com.rentflex.userservice.repository.UserRepository;
import com.rentflex.userservice.service.UserService;
import jakarta.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
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
                .Id(savedUser.getId())
                .userName(savedUser.getUserName())
                .email(savedUser.getEmail())
                .role(savedUser.getRole())
                .status(savedUser.getStatus())
                .message("User registered successfully")
                .build();
    }

    @Override
    public Response getUserProfileById(Long userId) {
        return null;
    }

    @Override
    public List<Response> getAllUserProfile() {
        return List.of();
    }

    @Override
    public Response updateUserProfile(Long userId) {
        return null;
    }

    @Override
    public void deleteUser(Long userId) {}

    @Override
    public void updateUserRole(Long userId) {}

    @Override
    public void updateUserStatus(Long userId) {}
}
