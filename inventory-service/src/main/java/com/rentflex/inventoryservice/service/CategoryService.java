package com.rentflex.inventoryservice.service;

import com.rentflex.inventoryservice.dto.CategoryRequest;
import com.rentflex.inventoryservice.dto.CategoryResponse;
import java.util.List;

public interface CategoryService {
  CategoryResponse createCategory(CategoryRequest categoryRequest);

  List<CategoryResponse> getAllCategories();

  CategoryResponse getCategoryById(Long categoryId);

  CategoryResponse updateCategory(Long categoryId, CategoryRequest categoryRequest);

  void deleteCategory(Long categoryId);
}
