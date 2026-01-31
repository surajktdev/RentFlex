package com.rentflex.bookingservice.client;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "inventory-service", url = "http://localhost:8083")
public interface InventoryClient {

    @GetMapping("/api/v1/item-availability/item/{itemId}")
    List<ItemAvailabilityResponse> getAvailabilityByItem(@PathVariable Long itemId);

    @PutMapping("/api/v1/item-availability/{id}")
    ItemAvailabilityResponse updateAvailability(
            @PathVariable("id") Long itemId, @RequestBody ItemAvailabilityRequest request);
}
