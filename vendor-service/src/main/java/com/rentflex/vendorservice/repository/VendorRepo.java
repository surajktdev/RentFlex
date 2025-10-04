package com.rentflex.vendorservice.repository;

import com.rentflex.vendorservice.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepo extends JpaRepository<Vendor, Long> {}
