package com.rentflex.inventoryservice.exception;

import lombok.Builder;

@Builder
public record ApiResponse(String message, boolean success) {}
