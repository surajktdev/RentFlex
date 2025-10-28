package com.rentflex.inventoryservice.repository;

import com.rentflex.inventoryservice.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ItemRepository extends JpaRepository<Item, Long> {

  //    boolean existsVendorId(Long vendorId);

  @Query("SELECT i FROM Item i WHERE i.vendorId = :vendorId")
  Item findByVendorId(Long vendorId);
}
