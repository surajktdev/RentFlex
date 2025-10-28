package com.rentflex.adminservice.feign;

import com.rentflex.adminservice.dto.AdminVendorResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "vendor-service", url = "${services.vendor-service.url}")
public interface VendorClient {

    @GetMapping("/api/vendors")
    List<AdminVendorResponse> getAllVendors();
}
