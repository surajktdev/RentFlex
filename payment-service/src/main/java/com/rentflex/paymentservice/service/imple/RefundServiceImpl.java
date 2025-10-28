package com.rentflex.paymentservice.service.imple;

import com.rentflex.paymentservice.dto.RefundRequestDTO;
import com.rentflex.paymentservice.dto.RefundResponseDTO;
import com.rentflex.paymentservice.model.Refund;
import com.rentflex.paymentservice.model.RefundStatus;
import com.rentflex.paymentservice.repository.RefundRepository;
import com.rentflex.paymentservice.service.RefundService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RefundServiceImpl implements RefundService {

    @Autowired private RefundRepository refundRepository;

    @Override
    public RefundResponseDTO initiateRefund(RefundRequestDTO request) {
        Refund refund = new Refund();
        refund.setTransactionId(request.transactionId());
        refund.setRefundAmount(request.refundAmount());
        refund.setReason(request.reason());
        refund.setRefundDate(LocalDateTime.now());
        refund.setRefundStatus(RefundStatus.INITIATED);

        refund = refundRepository.save(refund);
        return RefundResponseDTO.builder()
                .refundId(refund.getRefundId())
                .transactionId(refund.getTransactionId())
                .refundAmount(refund.getRefundAmount())
                .refundDate(refund.getRefundDate())
                .refundStatus(refund.getRefundStatus())
                .reason(refund.getReason())
                .build();
    }

    @Override
    public void updateRefundStatus(Long refundId, String status) {
        Refund refund =
                refundRepository
                        .findById(refundId)
                        .orElseThrow(() -> new RuntimeException("Refund not found"));

        refund.setRefundStatus(RefundStatus.valueOf(status));
        refundRepository.save(refund);
    }

    @Override
    public RefundResponseDTO getRefundById(Long refundId) {
        Refund refund =
                refundRepository
                        .findById(refundId)
                        .orElseThrow(() -> new RuntimeException("Refund not found"));
        return RefundResponseDTO.builder()
                .refundId(refund.getRefundId())
                .transactionId(refund.getTransactionId())
                .refundAmount(refund.getRefundAmount())
                .refundDate(refund.getRefundDate())
                .refundStatus(refund.getRefundStatus())
                .reason(refund.getReason())
                .build();
    }

    @Override
    public RefundResponseDTO getRefundByTransaction(String transactionId) {
        Refund refund =
                refundRepository
                        .findByTransactionId(transactionId)
                        .orElseThrow(() -> new RuntimeException("Refund not found"));
        return RefundResponseDTO.builder()
                .refundId(refund.getRefundId())
                .transactionId(refund.getTransactionId())
                .refundAmount(refund.getRefundAmount())
                .refundDate(refund.getRefundDate())
                .refundStatus(refund.getRefundStatus())
                .reason(refund.getReason())
                .build();
    }

    @Override
    public List<RefundResponseDTO> getAllRefunds() {
        return refundRepository.findAll().stream()
                .map(
                        refund ->
                                RefundResponseDTO.builder()
                                        .refundId(refund.getRefundId())
                                        .transactionId(refund.getTransactionId())
                                        .refundAmount(refund.getRefundAmount())
                                        .refundDate(refund.getRefundDate())
                                        .refundStatus(refund.getRefundStatus())
                                        .reason(refund.getReason())
                                        .build())
                .collect(Collectors.toList());
    }
}
