package com.rentflex.inventoryservice.controller;

import com.rentflex.inventoryservice.dto.ItemAvailabilityRequest;
import com.rentflex.inventoryservice.dto.ItemAvailabilityResponse;
import com.rentflex.inventoryservice.service.ItemAvailabilityService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/item-availability")
@Tag(
    name = "ItemAvailability Operations",
    description = "Endpoints for handling inventory item-availability-related functionalities")
public class ItemAvailabilityController {

  @Autowired private ItemAvailabilityService itemAvailabilityService;

  @PostMapping("/")
  public ResponseEntity<ItemAvailabilityResponse> setItemAvailability(
      @RequestBody ItemAvailabilityRequest request) {
    ItemAvailabilityResponse itemAvailabilityResponse =
        itemAvailabilityService.setItemAvailability(request);
    return new ResponseEntity<>(itemAvailabilityResponse, HttpStatus.CREATED);
  }

  @GetMapping("/item/{itemId}")
  public ResponseEntity<List<ItemAvailabilityResponse>> getAvailabilityByItem(
      @PathVariable Long itemId) {
    List<ItemAvailabilityResponse> availabilityByItem =
        itemAvailabilityService.getAvailabilityByItem(itemId);
    return new ResponseEntity<>(availabilityByItem, HttpStatus.FOUND);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ItemAvailabilityResponse> updateAvailability(
      @PathVariable Long id, @RequestBody ItemAvailabilityRequest request) {
    ItemAvailabilityResponse itemAvailabilityResponse =
        itemAvailabilityService.updateAvailability(id, request);
    return new ResponseEntity<>(itemAvailabilityResponse, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity deleteAvailability(@PathVariable Long id) {
    itemAvailabilityService.deleteAvailability(id);
    return ResponseEntity.ok("Item availability deleted successfully");
  }
}
