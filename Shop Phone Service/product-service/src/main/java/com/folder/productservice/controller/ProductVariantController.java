package com.folder.productservice.controller;

import com.folder.productservice.dto.request.CreateProductVariantRequest;
import com.folder.productservice.dto.request.UpdateProductVariantRequest;
import com.folder.productservice.dto.response.ApiResponse;
import com.folder.productservice.dto.response.ProductVariantResponse;
import com.folder.productservice.service.ProductVariantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/product-variants")
@RequiredArgsConstructor
public class ProductVariantController {

      private final ProductVariantService productVariantService;

      @PostMapping
      public ApiResponse<ProductVariantResponse> create(
              @Valid @RequestBody CreateProductVariantRequest request){

          return ApiResponse.success(
                  productVariantService.create(request)
          );
      }

      @PutMapping("/{id}")
      public ApiResponse<ProductVariantResponse> update(
              @PathVariable UUID id,
              @Valid @RequestBody UpdateProductVariantRequest request){

          return ApiResponse.success(
                  productVariantService.update(id, request)
          );
      }

      @DeleteMapping("/{id}")
      public ApiResponse<Void> delete(
              @PathVariable UUID id){

          productVariantService.delete(id);

          return ApiResponse.success("Delete product variant successfully");
      }

      @GetMapping("/{id}")
      public ApiResponse<ProductVariantResponse> getById(
              @PathVariable UUID id){

          return ApiResponse.success(
                  productVariantService.getById(id));
      }

      @GetMapping("/product/{productId}")
      public ApiResponse<List<ProductVariantResponse>> getByProductId(
              @PathVariable UUID productId){

          return ApiResponse.success(
                  productVariantService.getByProductId(productId)
          );
      }
}
