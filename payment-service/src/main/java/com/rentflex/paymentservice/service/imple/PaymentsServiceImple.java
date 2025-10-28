package com.rentflex.paymentservice.service.imple;

import com.rentflex.paymentservice.dto.PaymentRequestDTO;
import com.rentflex.paymentservice.dto.PaymentResponseDTO;
import com.rentflex.paymentservice.dto.PaymentStatusResponseDTO;
import com.rentflex.paymentservice.model.Payment;
import com.rentflex.paymentservice.model.PaymentStatus;
import com.rentflex.paymentservice.repository.PaymentRepository;
import com.rentflex.paymentservice.service.PaymentService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentsServiceImple implements PaymentService {

    @Autowired private PaymentRepository paymentRepository;

    @Override
    public PaymentResponseDTO initiatePayment(PaymentRequestDTO request) {
        Payment payment = new Payment();
        payment.setBookingId(request.bookingId());
        payment.setUserId(request.userId());
        payment.setVendorId(request.vendorId());
        payment.setAmount(request.amount());
        payment.setCurrency(request.currency());
        payment.setMethod(request.method());
        payment.setStatus(PaymentStatus.PENDING);
        payment.setPaymentDate(LocalDateTime.now());
        payment.setGatewayName(request.gatewayName());

        payment = paymentRepository.save(payment);

        return PaymentResponseDTO.builder()
                .paymentId(payment.getPaymentId())
                .bookingId(payment.getBookingId())
                .userId(payment.getUserId())
                .vendorId(payment.getVendorId())
                .amount(payment.getAmount())
                .currency(payment.getCurrency())
                .method(payment.getMethod())
                .status(payment.getStatus())
                .transactionId(payment.getTransactionId())
                .gatewayName(payment.getGatewayName())
                .paymentDate(payment.getPaymentDate())
                .build();
    }

    @Override
    public PaymentStatusResponseDTO verifyPayment(String transactionId) {
        Payment payment =
                paymentRepository
                        .findByTransactionId(transactionId)
                        .orElseThrow(() -> new RuntimeException("Payment not found"));

        return new PaymentStatusResponseDTO(
                transactionId, payment.getStatus(), "Payment verification completed");
    }

    @Override
    public PaymentResponseDTO updatePaymentStatus(Long paymentId, PaymentStatus status) {
        Payment payment =
                paymentRepository
                        .findById(paymentId)
                        .orElseThrow(() -> new RuntimeException("Payment not found"));

        payment.setStatus(status);
        paymentRepository.save(payment);
        return PaymentResponseDTO.builder()
                .paymentId(payment.getPaymentId())
                .bookingId(payment.getBookingId())
                .userId(payment.getUserId())
                .vendorId(payment.getVendorId())
                .amount(payment.getAmount())
                .currency(payment.getCurrency())
                .status(payment.getStatus())
                .method(payment.getMethod())
                .paymentDate(payment.getPaymentDate())
                .transactionId(payment.getTransactionId())
                .gatewayName(payment.getGatewayName())
                .message("Details Updated successfully.")
                .build();
    }

    @Override
    public PaymentResponseDTO getPaymentById(Long paymentId) {
        Payment payment =
                paymentRepository
                        .findById(paymentId)
                        .orElseThrow(() -> new RuntimeException("Payment not found"));
        return PaymentResponseDTO.builder()
                .paymentId(payment.getPaymentId())
                .bookingId(payment.getBookingId())
                .userId(payment.getUserId())
                .vendorId(payment.getVendorId())
                .amount(payment.getAmount())
                .currency(payment.getCurrency())
                .status(payment.getStatus())
                .method(payment.getMethod())
                .paymentDate(payment.getPaymentDate())
                .transactionId(payment.getTransactionId())
                .gatewayName(payment.getGatewayName())
                .build();
    }

    @Override
    public List<PaymentResponseDTO> getPaymentsByUser(Long userId) {
        return paymentRepository.findByUserId(userId).stream()
                .map(
                        payment ->
                                PaymentResponseDTO.builder()
                                        .paymentId(payment.getPaymentId())
                                        .bookingId(payment.getBookingId())
                                        .userId(payment.getUserId())
                                        .vendorId(payment.getVendorId())
                                        .amount(payment.getAmount())
                                        .currency(payment.getCurrency())
                                        .method(payment.getMethod())
                                        .status(payment.getStatus())
                                        .transactionId(payment.getTransactionId())
                                        .gatewayName(payment.getGatewayName())
                                        .paymentDate(payment.getPaymentDate())
                                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<PaymentResponseDTO> getPaymentsByVendor(Long vendorId) {
        return paymentRepository.findByVendorId(vendorId).stream()
                .map(
                        payment ->
                                PaymentResponseDTO.builder()
                                        .paymentId(payment.getPaymentId())
                                        .bookingId(payment.getBookingId())
                                        .userId(payment.getUserId())
                                        .vendorId(payment.getVendorId())
                                        .amount(payment.getAmount())
                                        .currency(payment.getCurrency())
                                        .method(payment.getMethod())
                                        .status(payment.getStatus())
                                        .transactionId(payment.getTransactionId())
                                        .gatewayName(payment.getGatewayName())
                                        .paymentDate(payment.getPaymentDate())
                                        .build())
                .collect(Collectors.toList());
    }
}
