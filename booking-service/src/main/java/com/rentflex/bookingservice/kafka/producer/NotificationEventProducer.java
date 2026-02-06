package com.rentflex.bookingservice.kafka.producer;

import com.rentflex.bookingservice.kafka.events.NotificationCreateEvent;
import com.rentflex.bookingservice.kafka.events.PaymentCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationEventProducer {

    private final KafkaTemplate<String, NotificationCreateEvent> kafkaTemplate;

    private static final String TOPIC = "booking.notification.request";

    public void sendBookingStatusEvent(NotificationCreateEvent event) {
        kafkaTemplate.send(TOPIC, String.valueOf(event.getBookingId()), event);
    }
}
