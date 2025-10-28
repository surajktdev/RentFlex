package com.rentflex.inventoryservice.dto;

import java.util.List;

public record ItemRequest(
    Long vendorId,
    String name,
    String description,
    Double pricePerDay,
    Boolean available,
    String location,
    Long categoryId,
    List<ItemAvailabilityRequest> availabilityList) {}
