package com.rentflex.paymentservice.repository;

import com.rentflex.paymentservice.model.Refund;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefundRepository extends JpaRepository<Refund, Long> {
    Optional<Refund> findByTransactionId(String transactionId);
}
