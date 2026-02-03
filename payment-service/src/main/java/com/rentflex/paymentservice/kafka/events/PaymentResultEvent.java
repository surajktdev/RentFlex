package com.rentflex.paymentservice.kafka.events;

import com.rentflex.paymentservice.model.PaymentStatus;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
