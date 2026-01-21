package com.rentflex.bookingservice.client;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemAvailabilityResponse {
  private Long id;
  private Long itemId;
  private LocalDateTime availableFrom;
  private LocalDateTime availableTo;
  private Boolean isAvailable;
  private String message;
}
