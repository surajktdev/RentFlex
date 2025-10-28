package com.rentflex.inventoryservice.service.impl;

import com.rentflex.inventoryservice.dto.CategoryRequest;
import com.rentflex.inventoryservice.dto.CategoryResponse;
import com.rentflex.inventoryservice.model.Category;
import com.rentflex.inventoryservice.repository.CategoryRepository;
import com.rentflex.inventoryservice.service.CategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

  @Autowired private CategoryRepository categoryRepository;

  @Override
  public CategoryResponse createCategory(CategoryRequest categoryRequest) {
    Category category = new Category();
    category.setName(categoryRequest.name());
    category.setDescription(categoryRequest.description());
    Category save = categoryRepository.save(category);
    return CategoryResponse.builder()
        .id(save.getId())
        .name(save.getName())
        .description(save.getDescription())
        .build();
  }

  @Override
  public List<CategoryResponse> getAllCategories() {
    List<Category> all = categoryRepository.findAll();
    return all.stream()
        .map(
            data ->
                CategoryResponse.builder()
                    .id(data.getId())
                    .name(data.getName())
                    .description(data.getDescription())
                    .build())
        .toList();
  }

  @Override
  public CategoryResponse getCategoryById(Long categoryId) {
    Category category =
        categoryRepository
            .findById(categoryId)
            .orElseThrow(
                () -> new RuntimeException("Category details not found for id: " + categoryId));
    return CategoryResponse.builder()
        .id(category.getId())
        .name(category.getName())
        .description(category.getDescription())
        .build();
  }

  @Override
  public CategoryResponse updateCategory(Long categoryId, CategoryRequest categoryRequest) {
    Category category =
        categoryRepository
            .findById(categoryId)
            .orElseThrow(
                () -> new RuntimeException("Category details not found for id: " + categoryId));

    category.setName(categoryRequest.name());
    category.setDescription(category.getDescription());
    Category save = categoryRepository.save(category);
    return CategoryResponse.builder()
        .id(save.getId())
        .name(save.getName())
        .description(save.getDescription())
        .build();
  }

  @Override
  public void deleteCategory(Long categoryId) {
    Category category =
        categoryRepository
            .findById(categoryId)
            .orElseThrow(
                () -> new RuntimeException("Category details not found for id: " + categoryId));
    categoryRepository.deleteById(category.getId());
  }
}
