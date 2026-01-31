package com.rentflex.bookingservice.client;

import java.time.LocalDateTime;

public record ItemAvailabilityRequest(
        Long itemId, LocalDateTime availableFrom, LocalDateTime availableTo, Boolean isAvailable) {}
