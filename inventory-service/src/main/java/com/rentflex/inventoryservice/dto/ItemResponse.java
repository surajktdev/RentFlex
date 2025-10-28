package com.rentflex.inventoryservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
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
