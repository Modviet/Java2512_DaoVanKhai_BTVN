package com.folder.productservice.service;

import com.folder.productservice.dto.request.CreateBrandRequest;
import com.folder.productservice.dto.request.UpdateBrandRequest;
import com.folder.productservice.dto.response.BrandResponse;

import java.util.List;
import java.util.UUID;

public interface BrandService {

       BrandResponse create(CreateBrandRequest request);

       BrandResponse update(UUID id, UpdateBrandRequest request);

       void delete(UUID id);

       BrandResponse getById(UUID id);

       List<BrandResponse> getAll();


}
