package com.rentflex.paymentservice.dto;

import com.rentflex.paymentservice.model.RefundStatus;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RefundResponseDTO {
    private Long refundId;
    private String transactionId;
    private Double refundAmount;
    private LocalDateTime refundDate;
    private RefundStatus refundStatus;
    private String reason;

    //    public RefundResponseDTO(Refund refund) {
    //        this.refundId = refund.getRefundId();
    //        this.transactionId = refund.getTransactionId();
    //        this.refundAmount = refund.getRefundAmount();
    //        this.refundDate = refund.getRefundDate();
    //        this.refundStatus = refund.getRefundStatus();
    //        this.reason = refund.getReason();
    //    }
}
