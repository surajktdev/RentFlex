package com.rentflex.paymentservice.repository;

import com.rentflex.paymentservice.model.Payment;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findByTransactionId(String transactionId);

    List<Payment> findByUserId(Long userId);

    List<Payment> findByVendorId(Long vendorId);
}
