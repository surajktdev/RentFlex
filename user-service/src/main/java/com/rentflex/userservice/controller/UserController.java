package com.rentflex.userservice.controller;

import com.rentflex.userservice.dto.Request;
import com.rentflex.userservice.dto.Response;
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
    public ResponseEntity<Response> register(@RequestBody Request request) {
        Response response = userService.registerUser(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a user profile by ID")
    public ResponseEntity<Response> getUserProfileById(@PathVariable("id") Long userId) {
        Response response = userService.getUserProfileById(userId);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Get all user profiles")
    public ResponseEntity<List<Response>> getAllUserProfiles() {
        List<Response> users = userService.getAllUserProfile();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a user profile by ID")
    public ResponseEntity<Response> updateUserProfile(
            @PathVariable("id") Long userId, @RequestBody Request request) {
        Response response = userService.updateUserProfile(userId, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a user by ID")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/role")
    @Operation(summary = "Update a user's role by ID")
    public ResponseEntity<Response> updateUserRole(
            @PathVariable("id") Long userId, @RequestParam("role") Role role) {
        Response response = userService.updateUserRole(userId, role);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Update a user's status by ID")
    public ResponseEntity<Response> updateUserStatus(
            @PathVariable("id") Long userId, @RequestParam("status") Status status) {
        Response response = userService.updateUserStatus(userId, status);
        return ResponseEntity.ok(response);
    }
}
