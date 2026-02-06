package com.rentflex.bookingservice.kafka.events;

import java.time.LocalDateTime;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentCreatedEvent {

    private Long bookingId;
    private Long userId;
    private Long itemId;
    private Double amount;
    private String currency;
    private LocalDateTime timestamp;
}
