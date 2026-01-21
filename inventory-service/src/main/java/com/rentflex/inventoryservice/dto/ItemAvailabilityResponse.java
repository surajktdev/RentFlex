package com.rentflex.inventoryservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

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
