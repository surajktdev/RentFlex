package com.rentflex.bookingservice.exception;

import lombok.Builder;

@Builder
public record ApiResponse(String message, boolean success) {}
