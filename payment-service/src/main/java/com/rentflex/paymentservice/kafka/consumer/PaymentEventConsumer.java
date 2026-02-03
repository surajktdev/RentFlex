package com.rentflex.paymentservice.kafka.consumer;

import com.rentflex.paymentservice.kafka.events.BookingCreatedEvent;
import com.rentflex.paymentservice.kafka.events.PaymentResultEvent;
import com.rentflex.paymentservice.kafka.producer.PaymentResultProducer;
import com.rentflex.paymentservice.model.Payment;
import com.rentflex.paymentservice.model.PaymentMethod;
import com.rentflex.paymentservice.model.PaymentStatus;
import com.rentflex.paymentservice.repository.PaymentRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentEventConsumer {

    private final PaymentRepository paymentRepository;
    private final PaymentResultProducer paymentResultProducer;

    @KafkaListener(topics = "booking.payment.request", groupId = "payment-service-group")
    public void consumeBookingEvent(BookingCreatedEvent event) {

        log.info(
                "Payment triggered | bookingId={} | userId={} | itemId={} | amount={}",
                event.getBookingId(),
                event.getUserId(),
                event.getItemId(),
                event.getAmount());

        // Save payment as INITIATED / SUCCESS
        Payment payment = new Payment();
        payment.setBookingId(event.getBookingId().toString());
        payment.setUserId(event.getUserId());
        payment.setAmount(event.getAmount());
        payment.setMethod(PaymentMethod.CASH_ON_DELIVERY);
        payment.setCurrency(event.getCurrency());
        payment.setStatus(PaymentStatus.SUCCESS);
        payment.setPaymentDate(LocalDateTime.now());

        payment = paymentRepository.save(payment);

        // Send payment result back to Booking Service
        PaymentResultEvent resultEvent =
                new PaymentResultEvent(
                        event.getBookingId(),
                        payment.getPaymentId(),
                        PaymentStatus.SUCCESS,
                        "Payment completed successfully",
                        LocalDateTime.now());

        paymentResultProducer.sendPaymentResult(resultEvent);

        // TODO:
        // 1. Call payment gateway (future)
    }
}
