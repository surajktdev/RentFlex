package com.rentflex.inventoryservice.service.impl;

import com.rentflex.inventoryservice.dto.ItemAvailabilityRequest;
import com.rentflex.inventoryservice.dto.ItemAvailabilityResponse;
import com.rentflex.inventoryservice.exception.ResourceNotFoundException;
import com.rentflex.inventoryservice.model.Item;
import com.rentflex.inventoryservice.model.ItemAvailability;
import com.rentflex.inventoryservice.repository.ItemAvailabilityRepository;
import com.rentflex.inventoryservice.repository.ItemRepository;
import com.rentflex.inventoryservice.service.ItemAvailabilityService;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
                    new ResourceNotFoundException(
                        "Item details not found. for itemId: " + availabilityRequest.itemId()));
    ItemAvailability itemAvailability = new ItemAvailability();
    itemAvailability.setItem(savedItem);
    itemAvailability.setAvailableFrom(LocalDateTime.now());
    itemAvailability.setAvailableTo(LocalDateTime.now());
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
  public ItemAvailabilityResponse getAvailabilityByItemId(Long itemId) {
    Item savedItem =
        itemRepository
            .findById(itemId)
            .orElseThrow(
                () ->
                    new ResourceNotFoundException("Item details not found. for itemId: " + itemId));
    ItemAvailability byId =
        availabilityRepository
            .findById(savedItem.getId())
            .orElseThrow(
                () ->
                    new ResourceNotFoundException("Item details not found. for itemId: " + itemId));
    return ItemAvailabilityResponse.builder()
        .itemId(byId.getId())
        .availableFrom(byId.getAvailableFrom())
        .availableTo(byId.getAvailableTo())
        .isAvailable(byId.getIsAvailable())
        .build();
  }

  @Override
  public List<ItemAvailabilityResponse> getAvailabilityByItemIds(List<Long> itemIds) {
    Item savedItem = null;
    for (Long itemId : itemIds) {
      savedItem =
          itemRepository
              .findById(itemId)
              .orElseThrow(
                  () ->
                      new ResourceNotFoundException(
                          "Item details not found. for itemId: " + itemId));
    }
    List<ItemAvailability> availabilities = availabilityRepository.findAllById(itemIds);

    List<ItemAvailabilityResponse> availabilityResponses = new ArrayList<>();
    for (ItemAvailability itemAvailability : availabilities) {
      availabilityResponses.add(
          ItemAvailabilityResponse.builder()
              .itemId(itemAvailability.getId())
              .availableFrom(itemAvailability.getAvailableFrom())
              .availableTo(itemAvailability.getAvailableTo())
              .isAvailable(itemAvailability.getIsAvailable())
              .build());
    }

    return availabilityResponses;
  }

  @Override
  public ItemAvailabilityResponse updateAvailability(
      Long availabilityId, ItemAvailabilityRequest availabilityRequest) {
    Item savedItem =
        itemRepository
            .findById(availabilityRequest.itemId())
            .orElseThrow(
                () ->
                    new ResourceNotFoundException(
                        "Item details not found. for itemId: " + availabilityRequest.itemId()));
    ItemAvailability itemAvailability =
        availabilityRepository
            .findById(availabilityId)
            .orElseThrow(
                () ->
                    new RuntimeException(
                        "Item availability details not found for id: " + availabilityId));
    itemAvailability.setItem(savedItem);
    itemAvailability.setAvailableFrom(availabilityRequest.availableFrom());
    itemAvailability.setAvailableTo(availabilityRequest.availableTo());
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
                    new ResourceNotFoundException(
                        "Item availability details not found for id: " + availabilityId));
    availabilityRepository.deleteById(itemAvailability.getId());
  }
}
