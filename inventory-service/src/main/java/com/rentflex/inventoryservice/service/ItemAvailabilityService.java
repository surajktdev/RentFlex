package com.rentflex.inventoryservice.service;

import com.rentflex.inventoryservice.dto.ItemAvailabilityRequest;
import com.rentflex.inventoryservice.dto.ItemAvailabilityResponse;
import java.util.List;

public interface ItemAvailabilityService {
  ItemAvailabilityResponse setItemAvailability(ItemAvailabilityRequest availabilityRequest);

  List<ItemAvailabilityResponse> getAvailabilityByItem(Long itemId);

  ItemAvailabilityResponse updateAvailability(
      Long availabilityId, ItemAvailabilityRequest availabilityRequest);

  void deleteAvailability(Long availabilityId);
}
