package com.rentflex.paymentservice.kafka.producer;

import com.rentflex.paymentservice.kafka.events.PaymentResultEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentResultProducer {

    private final KafkaTemplate<String, PaymentResultEvent> kafkaTemplate;

    private static final String TOPIC = "booking.payment.response";

    public void sendPaymentResult(PaymentResultEvent event) {
        kafkaTemplate.send(TOPIC, event.getBookingId().toString(), event);
    }
}
