package com.rentflex.userservice.dto;

import com.rentflex.userservice.model.Role;

public record Request(String userName, String email, String password, Role role) {}
