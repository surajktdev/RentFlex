package com.rentflex.vendorservice.repository;

import com.rentflex.vendorservice.model.Vendor;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepo extends JpaRepository<Vendor, Long> {

    @Query(value = "SELECT * FROM vendor WHERE vendor_Id = :vendorId", nativeQuery = true)
    Optional<Vendor> findByVendorId(Long vendorId);

    Optional<Vendor> findByUserId(Long userId);

    //    List<Vendor> findByStatus(Status status);

    boolean existsByEmail(String email);
}
