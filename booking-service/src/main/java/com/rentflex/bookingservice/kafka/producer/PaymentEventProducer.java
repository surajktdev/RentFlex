package com.rentflex.bookingservice.kafka.producer;

import com.rentflex.bookingservice.kafka.events.PaymentCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentEventProducer {

    private final KafkaTemplate<String, PaymentCreatedEvent> kafkaTemplate;

    private static final String TOPIC = "booking.payment.request";

    public void sendBookingCreatedEvent(PaymentCreatedEvent event) {
        kafkaTemplate.send(TOPIC, String.valueOf(event.getBookingId()), event);
    }
}
