package com.rentflex.inventoryservice.dto;

import java.time.LocalDate;

public record ItemAvailabilityRequest(
    Long itemId, LocalDate availableFrom, LocalDate availableTo, Boolean isAvailable) {}
