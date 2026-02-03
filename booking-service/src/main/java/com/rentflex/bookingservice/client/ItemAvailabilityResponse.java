package com.rentflex.bookingservice.client;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
import lombok.*;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class ItemAvailabilityResponse {
    private Long id;
    private Long itemId;
    private LocalDateTime availableFrom;
    private LocalDateTime availableTo;
    private Boolean isAvailable;
    private String message;
}
