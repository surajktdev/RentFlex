package com.rentflex.bookingservice.kafka.producer;

import com.rentflex.bookingservice.kafka.events.BookingCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingEventProducer {

    private final KafkaTemplate<String, BookingCreatedEvent> kafkaTemplate;

    private static final String TOPIC = "booking.payment.request";

    public void sendBookingCreatedEvent(BookingCreatedEvent event) {
        kafkaTemplate.send(TOPIC, String.valueOf(event.getBookingId()), event);
    }
}
