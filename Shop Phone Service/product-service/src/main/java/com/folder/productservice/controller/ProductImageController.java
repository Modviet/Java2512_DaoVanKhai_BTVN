package com.folder.productservice.controller;

import com.folder.productservice.dto.request.CreateProductImageRequest;
import com.folder.productservice.dto.request.UpdateProductImageRequest;
import com.folder.productservice.dto.request.UpdateProductRequest;
import com.folder.productservice.dto.response.ApiResponse;
import com.folder.productservice.dto.response.ProductImageResponse;
import com.folder.productservice.service.ProductImageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/product-images")
@RequiredArgsConstructor
public class ProductImageController {

       private final ProductImageService imageService;

       @PostMapping
       public ApiResponse<ProductImageResponse> create(
               @Valid @RequestBody CreateProductImageRequest request){

           return ApiResponse.success(
                   imageService.create(request)
           );
       }

    @PutMapping("/{id}")
    public ApiResponse<ProductImageResponse> update(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateProductImageRequest request) {

        return ApiResponse.success(
                imageService.update(id, request)
        );
    }

       @DeleteMapping("/{id}")
       public ApiResponse<Void> delete(
               @PathVariable UUID id) {

           imageService.delete(id);

           return ApiResponse.success("Delete product image successfully");
       }

       @GetMapping("/{id}")
       public ApiResponse<ProductImageResponse> getById(
               @PathVariable UUID id) {

           return ApiResponse.success(
                   imageService.getById(id)
           );
       }

       @GetMapping("/product/{productId}")
       public ApiResponse<List<ProductImageResponse>> getByProductId(
               @PathVariable UUID productId) {

           return ApiResponse.success(
                   imageService.getByProductId(productId)
           );
       }
}
