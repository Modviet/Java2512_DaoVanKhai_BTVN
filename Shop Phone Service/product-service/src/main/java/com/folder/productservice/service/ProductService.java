package com.folder.productservice.service;

import com.folder.productservice.dto.request.CreateProductRequest;
import com.folder.productservice.dto.request.ProductFilterRequest;
import com.folder.productservice.dto.request.UpdateProductRequest;
import com.folder.productservice.dto.response.ProductResponse;
import com.folder.productservice.dto.response.ProductSummaryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ProductService {

      ProductResponse create(CreateProductRequest request);

      ProductResponse update(UUID id,
                             UpdateProductRequest request);

      void delete(UUID id);

      ProductResponse getById(UUID id);

      Page<ProductSummaryResponse> getAll(
              ProductFilterRequest filter,
              Pageable pageable);
}
