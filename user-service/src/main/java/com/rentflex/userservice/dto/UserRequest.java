package com.rentflex.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rentflex.userservice.model.Role;
import jakarta.validation.constraints.*;

public record UserRequest(
        @JsonProperty("userName")
                @NotBlank(message = "Username is mandatory")
                @Size(min = 3, message = "Username must be at least 3 characters long")
                String userName,
        @NotBlank(message = "Email is required")
                @Email(message = "Please provide a valid email address")
                String email,
        @NotBlank(message = "Password is required")
                @Size(min = 3, message = "Password must be at least 8 characters long")
                String password,
        @NotNull(message = "Role is mandatory") Role role) {}
