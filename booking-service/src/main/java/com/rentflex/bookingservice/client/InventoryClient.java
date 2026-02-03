package com.rentflex.bookingservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "inventory-service", url = "http://localhost:8083")
public interface InventoryClient {

    @GetMapping("/api/v1/item-availability/item/{itemId}")
    ItemAvailabilityResponse getAvailabilityByItemId(@PathVariable Long itemId);

    @PutMapping("/api/v1/item-availability/updateAvailability")
    ItemAvailabilityResponse updateAvailability(@RequestBody ItemAvailabilityRequest request);

    @GetMapping("/api/v1/item/{id}")
    ItemResponse getItemById(@PathVariable Long id);
}
