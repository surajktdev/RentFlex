package com.rentflex.adminservice.dto;

import lombok.Builder;

@Builder
public record ManageUserRequest(
        Long id, String fullName, String email, String role, Boolean active) {}
