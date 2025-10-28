package com.rentflex.inventoryservice.service;

import com.rentflex.inventoryservice.dto.ItemRequest;
import com.rentflex.inventoryservice.dto.ItemResponse;
import java.util.List;

public interface ItemService {
  ItemResponse createItem(ItemRequest itemRequest);

  List<ItemResponse> getAllItems();

  ItemResponse getItemById(Long itemId);

  ItemResponse getItemsByVendor(Long vendorId);

  ItemResponse updateItem(Long itemId, ItemRequest itemRequest);

  void deleteItem(Long itemId);

  ItemResponse updateItemAvailability(Long itemId, Boolean available);

  ItemResponse searchItems(
      String keyword, Long categoryId, String location, Double minPrice, Double maxPrice);

  ItemResponse getAvailableItems();
}
