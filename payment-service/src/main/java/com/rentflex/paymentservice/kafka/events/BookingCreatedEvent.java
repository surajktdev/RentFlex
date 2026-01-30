package com.rentflex.paymentservice.kafka.events;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingCreatedEvent {

    private Long bookingId;
    private Long userId;
    private Long itemId;
    private Double amount;
    private String currency;
    private LocalDateTime timestamp;
}
