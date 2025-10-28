package com.rentflex.inventoryservice.service.impl;

import com.rentflex.inventoryservice.dto.ItemAvailabilityResponse;
import com.rentflex.inventoryservice.dto.ItemRequest;
import com.rentflex.inventoryservice.dto.ItemResponse;
import com.rentflex.inventoryservice.model.Category;
import com.rentflex.inventoryservice.model.Item;
import com.rentflex.inventoryservice.model.ItemAvailability;
import com.rentflex.inventoryservice.repository.CategoryRepository;
import com.rentflex.inventoryservice.repository.ItemRepository;
import com.rentflex.inventoryservice.service.ItemService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

  @Autowired private ItemRepository itemRepository;

  @Autowired private CategoryRepository categoryRepository;

  @Override
  public ItemResponse createItem(ItemRequest itemRequest) {
    Category category =
        categoryRepository
            .findById(itemRequest.categoryId())
            .orElseThrow(() -> new RuntimeException("Category not found"));

    Item item = new Item();
    item.setVendorId(itemRequest.vendorId());
    item.setName(itemRequest.name());
    item.setDescription(itemRequest.description());
    item.setPricePerDay(itemRequest.pricePerDay());
    item.setAvailable(itemRequest.available());
    item.setLocation(itemRequest.location());
    item.setCategory(category);

    if (itemRequest.availabilityList() != null && !itemRequest.availabilityList().isEmpty()) {
      List<ItemAvailability> availabilityList =
          itemRequest.availabilityList().stream()
              .map(
                  availDto ->
                      ItemAvailability.builder()
                          .item(item)
                          .availableFrom(availDto.availableFrom())
                          .availableTo(availDto.availableTo())
                          .isAvailable(availDto.isAvailable())
                          .build())
              .collect(Collectors.toList());

      item.setAvailabilityList(availabilityList);
    }

    Item savedItem = itemRepository.save(item);

    List<ItemAvailabilityResponse> availabilityResponseList =
        savedItem.getAvailabilityList() != null
            ? savedItem.getAvailabilityList().stream()
                .map(
                    a ->
                        ItemAvailabilityResponse.builder()
                            .id(a.getId())
                            .itemId(savedItem.getId())
                            .availableFrom(a.getAvailableFrom())
                            .availableTo(a.getAvailableTo())
                            .isAvailable(a.getIsAvailable())
                            .build())
                .collect(Collectors.toList())
            : null;

    return ItemResponse.builder()
        .id(savedItem.getId())
        .name(savedItem.getName())
        .description(savedItem.getDescription())
        .pricePerDay(savedItem.getPricePerDay())
        .available(savedItem.getAvailable())
        .location(savedItem.getLocation())
        .vendorId(savedItem.getVendorId())
        .categoryName(savedItem.getCategory().getName())
        .availabilityList(availabilityResponseList)
        .build();
  }

  @Override
  public List<ItemResponse> getAllItems() {
    List<Item> allItemsList = itemRepository.findAll();

    return allItemsList.stream()
        .map(
            item -> {
              List<ItemAvailabilityResponse> availabilityList = null;
              if (item.getAvailabilityList() != null) {
                availabilityList =
                    item.getAvailabilityList().stream()
                        .map(
                            a ->
                                ItemAvailabilityResponse.builder()
                                    .id(a.getId())
                                    .itemId(item.getId())
                                    .availableFrom(a.getAvailableFrom())
                                    .availableTo(a.getAvailableTo())
                                    .isAvailable(a.getIsAvailable())
                                    .build())
                        .collect(Collectors.toList());
              }

              return ItemResponse.builder()
                  .id(item.getId())
                  .name(item.getName())
                  .description(item.getDescription())
                  .pricePerDay(item.getPricePerDay())
                  .available(item.getAvailable())
                  .location(item.getLocation())
                  .vendorId(item.getVendorId())
                  .categoryName(item.getCategory().getName())
                  .availabilityList(availabilityList)
                  .build();
            })
        .collect(Collectors.toList());
  }

  @Override
  public ItemResponse getItemById(Long itemId) {
    Item savedItem =
        itemRepository
            .findById(itemId)
            .orElseThrow(
                () -> new RuntimeException("Item details not found. for itemId: " + itemId));
    List<ItemAvailabilityResponse> availabilityResponseList =
        savedItem.getAvailabilityList() != null
            ? savedItem.getAvailabilityList().stream()
                .map(
                    a ->
                        ItemAvailabilityResponse.builder()
                            .id(a.getId())
                            .itemId(savedItem.getId())
                            .availableFrom(a.getAvailableFrom())
                            .availableTo(a.getAvailableTo())
                            .isAvailable(a.getIsAvailable())
                            .build())
                .collect(Collectors.toList())
            : null;

    return ItemResponse.builder()
        .id(savedItem.getId())
        .name(savedItem.getName())
        .description(savedItem.getDescription())
        .pricePerDay(savedItem.getPricePerDay())
        .available(savedItem.getAvailable())
        .location(savedItem.getLocation())
        .vendorId(savedItem.getVendorId())
        .categoryName(savedItem.getCategory().getName())
        .availabilityList(availabilityResponseList)
        .build();
  }

  @Override
  public ItemResponse getItemsByVendor(Long vendorId) {
    Item byVendorId = itemRepository.findByVendorId(vendorId);
    if (vendorId == null) {
      throw new RuntimeException("Item details not found. for vendorId: " + vendorId);
    }
    List<ItemAvailabilityResponse> availabilityResponseList =
        byVendorId.getAvailabilityList() != null
            ? byVendorId.getAvailabilityList().stream()
                .map(
                    a ->
                        ItemAvailabilityResponse.builder()
                            .id(a.getId())
                            .itemId(byVendorId.getId())
                            .availableFrom(a.getAvailableFrom())
                            .availableTo(a.getAvailableTo())
                            .isAvailable(a.getIsAvailable())
                            .build())
                .collect(Collectors.toList())
            : null;

    return ItemResponse.builder()
        .id(byVendorId.getId())
        .name(byVendorId.getName())
        .description(byVendorId.getDescription())
        .pricePerDay(byVendorId.getPricePerDay())
        .available(byVendorId.getAvailable())
        .location(byVendorId.getLocation())
        .vendorId(byVendorId.getVendorId())
        .categoryName(byVendorId.getCategory().getName())
        .availabilityList(availabilityResponseList)
        .build();
  }

  @Override
  public ItemResponse updateItem(Long itemId, ItemRequest itemRequest) {

    Category category =
        categoryRepository
            .findById(itemRequest.categoryId())
            .orElseThrow(() -> new RuntimeException("Category not found"));

    Item savedItem =
        itemRepository
            .findById(itemId)
            .orElseThrow(
                () -> new RuntimeException("Item details not found. for itemId: " + itemId));

    savedItem.setVendorId(itemRequest.vendorId());
    savedItem.setName(itemRequest.name());
    savedItem.setDescription(itemRequest.description());
    savedItem.setPricePerDay(itemRequest.pricePerDay());
    savedItem.setAvailable(itemRequest.available());
    savedItem.setLocation(itemRequest.location());
    savedItem.setCategory(category);

    if (itemRequest.availabilityList() != null) {
      // Clear existing list
      savedItem.getAvailabilityList().clear();

      // Add new availability items
      itemRequest
          .availabilityList()
          .forEach(
              availDto -> {
                ItemAvailability availability =
                    ItemAvailability.builder()
                        .item(savedItem) // set owning side
                        .availableFrom(availDto.availableFrom())
                        .availableTo(availDto.availableTo())
                        .isAvailable(availDto.isAvailable())
                        .build();
                savedItem.getAvailabilityList().add(availability);
              });
    }

    Item updatedItem = itemRepository.save(savedItem);

    List<ItemAvailabilityResponse> availabilityResponseList =
        updatedItem.getAvailabilityList() != null
            ? updatedItem.getAvailabilityList().stream()
                .map(
                    a ->
                        ItemAvailabilityResponse.builder()
                            .id(a.getId())
                            .itemId(updatedItem.getId())
                            .availableFrom(a.getAvailableFrom())
                            .availableTo(a.getAvailableTo())
                            .isAvailable(a.getIsAvailable())
                            .build())
                .collect(Collectors.toList())
            : null;

    return ItemResponse.builder()
        .id(updatedItem.getId())
        .name(updatedItem.getName())
        .description(updatedItem.getDescription())
        .pricePerDay(updatedItem.getPricePerDay())
        .available(updatedItem.getAvailable())
        .location(updatedItem.getLocation())
        .vendorId(updatedItem.getVendorId())
        .categoryName(updatedItem.getCategory().getName())
        .availabilityList(availabilityResponseList)
        .build();
  }

  @Override
  public void deleteItem(Long itemId) {
    Item item =
        itemRepository.findById(itemId).orElseThrow(() -> new RuntimeException("Item not found"));
    itemRepository.deleteById(item.getId());
  }

  @Override
  public ItemResponse updateItemAvailability(Long itemId, Boolean available) {
    Item item =
        itemRepository.findById(itemId).orElseThrow(() -> new RuntimeException("Item not found"));
    item.setAvailable(available);
    Item save = itemRepository.save(item);
    return ItemResponse.builder()
        .id(save.getId())
        .name(save.getName())
        .message("Item availability updated successfully.")
        .build();
  }

  @Override
  public ItemResponse searchItems(
      String keyword, Long categoryId, String location, Double minPrice, Double maxPrice) {
    return null;
  }

  @Override
  public ItemResponse getAvailableItems() {
    return null;
  }
}
