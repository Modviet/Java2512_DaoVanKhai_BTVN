package com.folder.productservice.service;

import com.folder.productservice.dto.request.CreateCategoryRequest;
import com.folder.productservice.dto.request.UpdateCategoryRequest;
import com.folder.productservice.dto.response.CategoryResponse;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

      CategoryResponse create(CreateCategoryRequest request);

      CategoryResponse update(UUID id, UpdateCategoryRequest request);

      void delete(UUID id);

      CategoryResponse getById(UUID id);

      List<CategoryResponse> getAll();
}
