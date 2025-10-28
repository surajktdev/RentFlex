package com.rentflex.inventoryservice.controller;

import com.rentflex.inventoryservice.dto.CategoryRequest;
import com.rentflex.inventoryservice.dto.CategoryResponse;
import com.rentflex.inventoryservice.service.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
@Tag(
    name = "Category Operations",
    description = "Endpoints for handling inventory category-related functionalities")
public class CategoryController {

  @Autowired private CategoryService categoryService;

  @PostMapping("/")
  public ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryRequest request) {
    CategoryResponse category = categoryService.createCategory(request);
    return new ResponseEntity<>(category, HttpStatus.CREATED);
  }

  @GetMapping("/")
  public ResponseEntity<List<CategoryResponse>> getAllCategories() {
    List<CategoryResponse> allCategories = categoryService.getAllCategories();
    return new ResponseEntity<>(allCategories, HttpStatus.FOUND);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable Long id) {
    CategoryResponse categoryById = categoryService.getCategoryById(id);
    return new ResponseEntity<>(categoryById, HttpStatus.FOUND);
  }

  @PutMapping("/{id}")
  public ResponseEntity<CategoryResponse> updateCategory(
      @PathVariable Long id, @RequestBody CategoryRequest request) {
    CategoryResponse categoryResponse = categoryService.updateCategory(id, request);
    return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
    categoryService.deleteCategory(id);
    return ResponseEntity.ok("Item availability deleted successfully");
  }
}
