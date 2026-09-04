package com.folder.productservice.controller;

import com.folder.productservice.dto.request.CreateCategoryRequest;
import com.folder.productservice.dto.request.UpdateCategoryRequest;
import com.folder.productservice.dto.response.ApiResponse;
import com.folder.productservice.dto.response.CategoryResponse;
import com.folder.productservice.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

     private final CategoryService categoryService;

     @PostMapping
     public ApiResponse<CategoryResponse> create(
             @Valid @RequestBody CreateCategoryRequest request) {

         return ApiResponse.success(categoryService.create(request));
     }

     @PutMapping("/{id}")
     public ApiResponse<CategoryResponse> update(
             @PathVariable UUID id,
             @Valid @RequestBody UpdateCategoryRequest request) {

         return ApiResponse.success(
                 categoryService.update(id, request));
     }

     @DeleteMapping("/{id}")
     public ApiResponse<Void> delete(
             @PathVariable UUID id
     ) {

         categoryService.delete(id);

         return ApiResponse.success("Delete category successfully");
     }

     @GetMapping("/{id}")
     public ApiResponse<CategoryResponse> getById(
             @PathVariable UUID id
     ){

         return ApiResponse
                 .success(categoryService.getById(id));
     }

     @GetMapping
     public ApiResponse<List<CategoryResponse>> getAll() {

            return ApiResponse.success(categoryService.getAll());
     }
}
