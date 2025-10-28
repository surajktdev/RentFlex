package com.rentflex.bookingservice.dto;

import com.rentflex.bookingservice.model.BookingStatus;
import java.time.LocalDateTime;

public record BookingRequestDTO(
        Long userId,
        Long itemId,
        LocalDateTime startDate,
        LocalDateTime endDate,
        BookingStatus status) {}
