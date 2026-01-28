package com.rentflex.userservice.controller;

import com.rentflex.userservice.auth.AuthRequest;
import com.rentflex.userservice.auth.AuthResponse;
import com.rentflex.userservice.auth.JwtUtil;
import com.rentflex.userservice.dto.UserRequest;
import com.rentflex.userservice.dto.UserResponse;
import com.rentflex.userservice.model.User;
import com.rentflex.userservice.repository.UserRepository;
import com.rentflex.userservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth/v1/")
@Tag(
        name = "Authentication",
        description = "Endpoints for handling user authentication and authorization")
@Slf4j
public class PublicController {

    @Autowired private UserService userService;

    @Autowired private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired private JwtUtil jwtUtil;

    @PostMapping(value = "/register")
    @Operation(summary = "Register New User")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody UserRequest userRequest) {
        UserResponse userResponse = userService.registerUser(userRequest);
        return ResponseEntity.ok(userResponse);
    }

    @PostMapping("/login")
    @Operation(summary = "generate token")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.email(), request.password()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(request.email());
            String generatedToken = jwtUtil.generateToken(userDetails.getUsername());
            User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow(
                    () ->
                            new UsernameNotFoundException(
                                    "User not found with email: " + userDetails.getUsername()));;
            UserResponse userResponse =
                    UserResponse.builder().id(user.getId()).userName(user.getUserName()).email(user.getEmail()).role(user.getRole()).status(user.getStatus()).build();
            AuthResponse response =
                    AuthResponse.builder().token(generatedToken).userResponse(userResponse).build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Exception occurred while createAuthenticationToken ", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(AuthResponse.builder().message("Incorrect username or password").build());
        }
    }
}
