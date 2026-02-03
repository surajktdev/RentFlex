package com.rentflex.inventoryservice.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

@Entity
@Table(name = "item_availability")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemAvailability {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "item_id", nullable = false)
  private Item item;

  @Column(nullable = false)
  private LocalDateTime availableFrom;

  @Column(nullable = false)
  private LocalDateTime availableTo;

  @Column(nullable = false)
  private Boolean isAvailable;
}
