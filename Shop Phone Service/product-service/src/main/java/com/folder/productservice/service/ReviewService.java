package com.folder.productservice.service;

import com.folder.productservice.dto.request.CreateReviewRequest;
import com.folder.productservice.dto.request.UpdateReviewRequest;
import com.folder.productservice.dto.response.ReviewResponse;

import java.util.List;
import java.util.UUID;

public interface ReviewService {

       ReviewResponse create(CreateReviewRequest request, UUID userId);

       ReviewResponse update(UUID id,
                             UpdateReviewRequest request,
                             UUID userId);

       void delete(UUID id, UUID userId);

       ReviewResponse getById(UUID id);

       List<ReviewResponse> getByProductId(UUID productId);

}
