package com.folder.productservice.controller;

import com.folder.productservice.dto.request.CreateReviewRequest;
import com.folder.productservice.dto.request.UpdateReviewRequest;
import com.folder.productservice.dto.response.ApiResponse;
import com.folder.productservice.dto.response.ReviewResponse;
import com.folder.productservice.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {

      private final ReviewService reviewService;

      @PostMapping
      public ApiResponse<ReviewResponse> create(
              @AuthenticationPrincipal Jwt jwt,
              @Valid @RequestBody CreateReviewRequest request) {

          UUID userId = UUID.fromString(jwt.getSubject());

          return ApiResponse.success(
                  reviewService.create(request, userId)
          );
      }

      @PutMapping("/{id}")
      public ApiResponse<ReviewResponse> update(
              @PathVariable UUID id,
              @AuthenticationPrincipal Jwt jwt,
              @Valid @RequestBody UpdateReviewRequest request) {

          UUID userId = UUID.fromString(jwt.getSubject());

          return ApiResponse.success(
                  reviewService.update(id, request, userId)
          );
      }

      @DeleteMapping("/{id}")
      public ApiResponse<Void> delete(
              @PathVariable UUID id,
              @AuthenticationPrincipal Jwt jwt){

          UUID userId = UUID.fromString(jwt.getSubject());

          reviewService.delete(id, userId);

          return ApiResponse.success("Delete review successfully");
      }

      @GetMapping("/{id}")
      public ApiResponse<ReviewResponse> getById(
              @PathVariable UUID id) {

          return ApiResponse.success(
                  reviewService.getById(id)
          );
      }

      @GetMapping("/product/{productId}")
      public ApiResponse<List<ReviewResponse>> getByProductId(
              @PathVariable UUID productId) {

          return ApiResponse.success(
                  reviewService.getByProductId(productId)
          );
      }

}
