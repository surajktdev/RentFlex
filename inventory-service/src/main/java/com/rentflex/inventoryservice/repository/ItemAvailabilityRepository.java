package com.rentflex.inventoryservice.repository;

import com.rentflex.inventoryservice.model.ItemAvailability;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ItemAvailabilityRepository extends JpaRepository<ItemAvailability, Long> {

  // JPQL query
  //    @Query("SELECT i FROM ItemAvailability i WHERE i.item.id = :itemId")
  //    List<ItemAvailability> findByItemId(@Param("itemId") Long itemId);

  @Query(value = "SELECT * FROM item_availability WHERE item_id = :itemId", nativeQuery = true)
  List<ItemAvailability> findByItemId(@Param("itemId") Long itemId);
}
