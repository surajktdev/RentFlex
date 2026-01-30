package com.rentflex.paymentservice.kafka.consumer;

import com.rentflex.paymentservice.kafka.events.BookingCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentEventConsumer {

    @KafkaListener(topics = "booking.payment.request", groupId = "payment-service-group")
    public void consumeBookingEvent(BookingCreatedEvent event) {

        log.info(
                "Payment triggered | bookingId={} | userId={} | itemId={} | amount={}",
                event.getBookingId(),
                event.getUserId(),
                event.getItemId(),
                event.getAmount());

        // TODO:
        // 1. Save payment with status INITIATED
        // 2. Call payment gateway (future)
    }
}
