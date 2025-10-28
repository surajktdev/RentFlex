package com.rentflex.inventoryservice.repository;

import com.rentflex.inventoryservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {}
