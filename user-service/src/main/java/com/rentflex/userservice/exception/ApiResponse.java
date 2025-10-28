package com.rentflex.userservice.exception;

import lombok.Builder;

@Builder
public record ApiResponse(String message, boolean success) {}
