package com.rentflex.userservice.dto;

import com.rentflex.userservice.model.Role;

public record UserRequest(String userName, String email, String password, Role role) {}
