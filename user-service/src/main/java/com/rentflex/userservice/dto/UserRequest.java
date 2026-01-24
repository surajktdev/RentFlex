package com.rentflex.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rentflex.userservice.model.Role;

public record UserRequest(
        @JsonProperty("name")
        String userName, String email, String password, Role role) {}
