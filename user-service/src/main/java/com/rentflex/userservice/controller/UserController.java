package com.rentflex.userservice.controller;

import com.rentflex.userservice.dto.UserRequest;
import com.rentflex.userservice.dto.UserResponse;
import com.rentflex.userservice.model.Role;
import com.rentflex.userservice.model.Status;
import com.rentflex.userservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "User Operations", description = "Endpoints for handling user-related functionalities")
public class UserController {

    @Autowired private UserService userService;

    @PostMapping(value = "/register")
    @Operation(summary = "Register New User")
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest userRequest) {
        UserResponse userResponse = userService.registerUser(userRequest);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a user profile by ID")
    public ResponseEntity<UserResponse> getUserProfileById(@PathVariable("id") Long userId) {
        UserResponse userResponse = userService.getUserProfileById(userId);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/")
    @Operation(summary = "Get all user profiles")
    public ResponseEntity<List<UserResponse>> getAllUserProfiles() {
        List<UserResponse> users = userService.getAllUserProfile();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a user profile by ID")
    public ResponseEntity<UserResponse> updateUserProfile(
            @PathVariable("id") Long userId, @RequestBody UserRequest userRequest) {
        UserResponse userResponse = userService.updateUserProfile(userId, userRequest);
        return ResponseEntity.ok(userResponse);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a user by ID")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/role")
    @Operation(summary = "Update a user's role by ID")
    public ResponseEntity<UserResponse> updateUserRole(
            @PathVariable("id") Long userId, @RequestParam("role") Role role) {
        UserResponse userResponse = userService.updateUserRole(userId, role);
        return ResponseEntity.ok(userResponse);
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Update a user's status by ID")
    public ResponseEntity<UserResponse> updateUserStatus(
            @PathVariable("id") Long userId, @RequestParam("status") Status status) {
        UserResponse userResponse = userService.updateUserStatus(userId, status);
        return ResponseEntity.ok(userResponse);
    }
}
