package com.rentflex.bookingservice.client;

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
