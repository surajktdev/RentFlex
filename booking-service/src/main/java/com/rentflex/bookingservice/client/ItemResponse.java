package com.rentflex.bookingservice.client;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.*;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class ItemResponse {
    private Long id;
    private Long vendorId;
    private String name;
    private String description;
    private Double pricePerDay;
    private Boolean available;
    private String location;
    private String categoryName;
    private List<ItemAvailabilityResponse> availabilityList;
    private String message;
}
