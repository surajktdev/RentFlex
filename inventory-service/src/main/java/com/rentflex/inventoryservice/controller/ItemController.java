package com.rentflex.inventoryservice.controller;

import com.rentflex.inventoryservice.dto.ItemRequest;
import com.rentflex.inventoryservice.dto.ItemResponse;
import com.rentflex.inventoryservice.service.ItemService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/item")
@Tag(
    name = "Item Operations",
    description = "Endpoints for handling inventory item-related functionalities")
public class ItemController {

  @Autowired private ItemService itemService;

  @PostMapping("/")
  public ResponseEntity<ItemResponse> createItem(@RequestBody ItemRequest request) {
    ItemResponse item = itemService.createItem(request);
    return new ResponseEntity<>(item, HttpStatus.CREATED);
  }

  @GetMapping("/")
  public ResponseEntity<List<ItemResponse>> getAllItems() {
    List<ItemResponse> allItems = itemService.getAllItems();
    return new ResponseEntity<>(allItems, HttpStatus.FOUND);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ItemResponse> getItemById(@PathVariable Long id) {
    ItemResponse itemById = itemService.getItemById(id);
    return new ResponseEntity<>(itemById, HttpStatus.FOUND);
  }

  @GetMapping("/{vendorId}/vendor")
  public ResponseEntity<ItemResponse> getItemsByVendor(@PathVariable Long vendorId) {
    ItemResponse itemsByVendor = itemService.getItemsByVendor(vendorId);
    return new ResponseEntity<>(itemsByVendor, HttpStatus.FOUND);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ItemResponse> updateItem(
      @PathVariable Long id, @RequestBody ItemRequest request) {
    ItemResponse itemResponse = itemService.updateItem(id, request);
    return new ResponseEntity<>(itemResponse, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteItem(@PathVariable Long id) {
    itemService.deleteItem(id);
    return ResponseEntity.ok("Item deleted successfully");
  }

  @PatchMapping("/{id}/availability")
  public ResponseEntity<ItemResponse> updateItemAvailability(
      @PathVariable Long id, @RequestBody ItemRequest request) {
    ItemResponse itemResponse = itemService.updateItemAvailability(id, request.available());
    return ResponseEntity.ok(itemResponse);
  }
}
