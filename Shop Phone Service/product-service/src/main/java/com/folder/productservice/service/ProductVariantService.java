package com.folder.productservice.service;

import com.folder.productservice.dto.request.CreateProductVariantRequest;
import com.folder.productservice.dto.request.UpdateProductVariantRequest;
import com.folder.productservice.dto.response.ProductVariantResponse;

import java.util.List;
import java.util.UUID;

public interface ProductVariantService {

       ProductVariantResponse create(CreateProductVariantRequest request);

       ProductVariantResponse update(UUID id,
                                     UpdateProductVariantRequest request);

       void delete(UUID id);

       ProductVariantResponse getById(UUID id);

       List<ProductVariantResponse> getByProductId(UUID productId);
}
