package com.rentflex.paymentservice.controller;

import com.rentflex.paymentservice.dto.*;
import com.rentflex.paymentservice.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
@Tag(name = "Payment Service", description = "APIs for managing payment processing and refunds")
public class PaymentController {

    @Autowired private PaymentService paymentService;

    @PostMapping("/initiate")
    @Operation(
            summary = "Initiate a new payment",
            description = "Starts a payment transaction for a booking")
    public PaymentResponseDTO initiatePayment(@RequestBody PaymentRequestDTO request) {
        return paymentService.initiatePayment(request);
    }

    @PostMapping("/verify")
    @Operation(
            summary = "Verify payment status",
            description = "Verifies payment transaction with payment gateway")
    public PaymentStatusResponseDTO verifyPayment(@RequestParam String transactionId) {
        return paymentService.verifyPayment(transactionId);
    }

    @GetMapping("/{paymentId}")
    @Operation(
            summary = "Get payment details by ID",
            description = "Fetches details of a specific payment")
    public PaymentResponseDTO getPaymentById(@PathVariable Long paymentId) {
        return paymentService.getPaymentById(paymentId);
    }

    @GetMapping("/user/{userId}")
    @Operation(
            summary = "Get payments by user ID",
            description = "Fetches all payments made by a user")
    public List<PaymentResponseDTO> getPaymentsByUser(@PathVariable Long userId) {
        return paymentService.getPaymentsByUser(userId);
    }

    @GetMapping("/vendor/{vendorId}")
    @Operation(
            summary = "Get payments by vendor ID",
            description = "Fetches all payments received by a vendor")
    public List<PaymentResponseDTO> getPaymentsByVendor(@PathVariable Long vendorId) {
        return paymentService.getPaymentsByVendor(vendorId);
    }
}
