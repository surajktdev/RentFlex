package com.rentflex.bookingservice.kafka.consumer;

import com.rentflex.bookingservice.kafka.events.NotificationCreateEvent;
import com.rentflex.bookingservice.kafka.events.PaymentResultEvent;
import com.rentflex.bookingservice.kafka.producer.NotificationEventProducer;
import com.rentflex.bookingservice.model.Booking;
import com.rentflex.bookingservice.model.BookingStatus;
import com.rentflex.bookingservice.model.PaymentInfo;
import com.rentflex.bookingservice.model.PaymentStatus;
import com.rentflex.bookingservice.repository.BookingRepository;
import com.rentflex.bookingservice.repository.PaymentInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentResultConsumer {

    private final PaymentInfoRepository paymentInfoRepository;
    private final BookingRepository bookingRepository;
    private final NotificationEventProducer notificationEventProducer;

    @KafkaListener(topics = "booking.payment.response", groupId = "booking-service-group")
    public void handlePaymentResult(PaymentResultEvent event) {

        log.info("Payment result received for bookingId={}", event.getBookingId());

        // Save payment info
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setBookingId(event.getBookingId());
        paymentInfo.setStatus(event.getStatus());

        paymentInfoRepository.save(paymentInfo);

        // Update booking status
        Booking booking = bookingRepository.findById(event.getBookingId()).orElseThrow();

        if (event.getStatus() == PaymentStatus.SUCCESS) {
            booking.setStatus(BookingStatus.CONFIRMED);
        } else {
            booking.setStatus(BookingStatus.PAYMENT_FAILED);
        }

        Booking saved = bookingRepository.save(booking);
        notificationEventProducer.sendBookingStatusEvent(
                new NotificationCreateEvent(
                        saved.getId(),
                        saved.getUserId(),
                        saved.getItemId(),
                        saved.getStatus()));
    }
}
