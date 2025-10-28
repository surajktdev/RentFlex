package com.rentflex.vendorservice.repository;

import com.rentflex.vendorservice.model.Vendor;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepo extends JpaRepository<Vendor, Long> {

    Optional<Vendor> findByUserId(Long userId);

    //    List<Vendor> findByStatus(Status status);

    boolean existsByEmail(String email);
}
