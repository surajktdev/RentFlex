package com.rentflex.bookingservice.repository;

import com.rentflex.bookingservice.model.PaymentInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentInfoRepository extends JpaRepository<PaymentInfo, Long> {}
