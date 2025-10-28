package com.rentflex.vendorservice.exception;

import lombok.Builder;

@Builder
public record ApiResponse(String message, boolean success) {}
