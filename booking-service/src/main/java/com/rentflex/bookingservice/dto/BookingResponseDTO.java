package com.rentflex.bookingservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rentflex.bookingservice.model.BookingStatus;
import java.time.LocalDateTime;
import lombok.*;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingResponseDTO {
    private Long bookingId;
    private BookingStatus status;
    private Double totalPrice;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String message;
}
