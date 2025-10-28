package com.rentflex.inventoryservice.service.impl;

import com.rentflex.inventoryservice.dto.ItemAvailabilityRequest;
import com.rentflex.inventoryservice.dto.ItemAvailabilityResponse;
import com.rentflex.inventoryservice.model.Item;
import com.rentflex.inventoryservice.model.ItemAvailability;
import com.rentflex.inventoryservice.repository.ItemAvailabilityRepository;
import com.rentflex.inventoryservice.repository.ItemRepository;
import com.rentflex.inventoryservice.service.ItemAvailabilityService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemAvailabilityServiceImpl implements ItemAvailabilityService {
  @Autowired private ItemAvailabilityRepository availabilityRepository;
  @Autowired private ItemRepository itemRepository;

  @Override
  public ItemAvailabilityResponse setItemAvailability(ItemAvailabilityRequest availabilityRequest) {
    Item savedItem =
        itemRepository
            .findById(availabilityRequest.itemId())
            .orElseThrow(
                () ->
                    new RuntimeException(
                        "Item details not found. for itemId: " + availabilityRequest.itemId()));
    ItemAvailability itemAvailability = new ItemAvailability();
    itemAvailability.setItem(savedItem);
    itemAvailability.setAvailableFrom(LocalDate.now());
    itemAvailability.setAvailableTo(LocalDate.now());
    itemAvailability.setIsAvailable(availabilityRequest.isAvailable());
    ItemAvailability save = availabilityRepository.save(itemAvailability);
    return ItemAvailabilityResponse.builder()
        .itemId(save.getItem().getId())
        .availableFrom(save.getAvailableFrom())
        .availableTo(save.getAvailableTo())
        .isAvailable(save.getIsAvailable())
        .build();
  }

  @Override
  public List<ItemAvailabilityResponse> getAvailabilityByItem(Long itemId) {
    Item savedItem =
        itemRepository
            .findById(itemId)
            .orElseThrow(
                () -> new RuntimeException("Item details not found. for itemId: " + itemId));
    List<ItemAvailability> byItemId = availabilityRepository.findByItemId(savedItem.getId());
    return byItemId.stream()
        .map(
            byId ->
                ItemAvailabilityResponse.builder()
                    .itemId(byId.getItem().getId())
                    .availableFrom(byId.getAvailableFrom())
                    .availableTo(byId.getAvailableTo())
                    .isAvailable(byId.getIsAvailable())
                    .build())
        .toList();
  }

  @Override
  public ItemAvailabilityResponse updateAvailability(
      Long availabilityId, ItemAvailabilityRequest availabilityRequest) {
    Item savedItem =
        itemRepository
            .findById(availabilityRequest.itemId())
            .orElseThrow(
                () ->
                    new RuntimeException(
                        "Item details not found. for itemId: " + availabilityRequest.itemId()));
    ItemAvailability itemAvailability =
        availabilityRepository
            .findById(availabilityId)
            .orElseThrow(
                () ->
                    new RuntimeException(
                        "Item availability details not found for id: " + availabilityId));
    itemAvailability.setItem(savedItem);
    itemAvailability.setAvailableFrom(LocalDate.now());
    itemAvailability.setAvailableTo(LocalDate.now());
    itemAvailability.setIsAvailable(availabilityRequest.isAvailable());
    ItemAvailability save = availabilityRepository.save(itemAvailability);

    return ItemAvailabilityResponse.builder()
        .itemId(save.getItem().getId())
        .availableFrom(save.getAvailableFrom())
        .availableTo(save.getAvailableTo())
        .isAvailable(save.getIsAvailable())
        .build();
  }

  @Override
  public void deleteAvailability(Long availabilityId) {
    ItemAvailability itemAvailability =
        availabilityRepository
            .findById(availabilityId)
            .orElseThrow(
                () ->
                    new RuntimeException(
                        "Item availability details not found for id: " + availabilityId));
    availabilityRepository.deleteById(itemAvailability.getId());
  }
}
