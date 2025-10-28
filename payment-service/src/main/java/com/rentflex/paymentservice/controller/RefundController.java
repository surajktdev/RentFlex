package com.rentflex.paymentservice.controller;

import com.rentflex.paymentservice.dto.RefundRequestDTO;
import com.rentflex.paymentservice.dto.RefundResponseDTO;
import com.rentflex.paymentservice.service.RefundService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/refunds")
@Tag(name = "Refund API", description = "Endpoints for refund management")
public class RefundController {

    @Autowired private RefundService refundService;

    @PostMapping("/initiate")
    @Operation(
            summary = "Initiate a refund",
            description = "Starts a refund process for a completed payment")
    public RefundResponseDTO initiateRefund(@RequestBody RefundRequestDTO request) {
        return refundService.initiateRefund(request);
    }

    @GetMapping("/{refundId}")
    @Operation(
            summary = "Get refund details by ID",
            description = "Fetches details of a specific refund")
    public RefundResponseDTO getRefundById(@PathVariable Long refundId) {
        return refundService.getRefundById(refundId);
    }

    @GetMapping("/transaction/{transactionId}")
    @Operation(
            summary = "Get refund by transaction ID",
            description = "Fetches refund details using the payment transaction ID")
    public RefundResponseDTO getRefundByTransaction(@PathVariable String transactionId) {
        return refundService.getRefundByTransaction(transactionId);
    }

    @GetMapping("/all")
    @Operation(
            summary = "Get all refunds",
            description = "Fetches all refund transactions in the system")
    public List<RefundResponseDTO> getAllRefunds() {
        return refundService.getAllRefunds();
    }
}
