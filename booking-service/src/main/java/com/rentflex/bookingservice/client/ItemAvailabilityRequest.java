package com.rentflex.bookingservice.client;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ItemAvailabilityRequest(
        Long itemId, LocalDateTime availableFrom, LocalDateTime availableTo, Boolean isAvailable) {}
