package com.rentflex.bookingservice.kafka.events;

import com.rentflex.bookingservice.model.PaymentStatus;
import java.time.LocalDateTime;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResultEvent {

    private Long bookingId;
    private Long paymentId;
    private PaymentStatus status;
    private String message;
    private LocalDateTime timestamp;
}
