package com.rentflex.notificationservice.kafka.consumer;

import com.rentflex.notificationservice.kafka.event.NotificationCreateEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationEventConsumer {

    @KafkaListener(topics = "booking.notification.request", groupId = "notification-service-group")
    public void consumeBookingEvent(NotificationCreateEvent event) {

        log.info("Notification triggered | bookingId={} | userId={} | itemId={} | status={}",
                event.getBookingId(),
                event.getUserId(),
                event.getItemId(),
                event.getStatus());

    }
}
