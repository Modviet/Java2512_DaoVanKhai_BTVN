package com.folder.productservice.service;

import com.folder.productservice.dto.request.CreateProductImageRequest;
import com.folder.productservice.dto.request.UpdateProductImageRequest;
import com.folder.productservice.dto.response.ProductImageResponse;

import java.util.List;
import java.util.UUID;

public interface ProductImageService {

      ProductImageResponse create(CreateProductImageRequest request);

      ProductImageResponse update(UUID id,
                                  UpdateProductImageRequest request);

      void delete(UUID id);

      ProductImageResponse getById(UUID id);

      List<ProductImageResponse> getByProductId(UUID productId);
}
