package com.rentflex.inventoryservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "vendor-service", url = "http://localhost:8087/")
public interface VendorClient {

  @GetMapping("/api/v1/vendor/{vendorId}")
  VendorResponse getVendorById(@PathVariable Long vendorId);
}
