package com.folder.productservice.controller;

import com.folder.productservice.dto.request.CreateProductRequest;
import com.folder.productservice.dto.request.ProductFilterRequest;
import com.folder.productservice.dto.request.UpdateProductRequest;
import com.folder.productservice.dto.response.ApiResponse;
import com.folder.productservice.dto.response.ProductResponse;
import com.folder.productservice.dto.response.ProductSummaryResponse;
import com.folder.productservice.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

       private final ProductService productService;

       @PostMapping
       public ApiResponse<ProductResponse> create(
               @Valid @RequestBody CreateProductRequest request){

              return ApiResponse.success(
                      productService.create(request)
              );
       }

       @PutMapping("/{id}")
       public ApiResponse<ProductResponse> update(
               @PathVariable UUID id,
               @Valid @RequestBody UpdateProductRequest request){

              return ApiResponse.success(
                      productService.update(id, request)
              );
       }

       @DeleteMapping("/{id}")
       public ApiResponse<Void> delete(
               @PathVariable UUID id
       ){

              productService.delete(id);

              return ApiResponse.success("Delete product successfully");
       }

       @GetMapping("/{id}")
       public ApiResponse<ProductResponse> getById(
               @PathVariable UUID id
       ){

              return ApiResponse.success(
                      productService.getById(id)
              );
       }

       @GetMapping
       public ApiResponse<Page<ProductSummaryResponse>> getAll(
               ProductFilterRequest filter,
               @PageableDefault(
                       size = 20,
                       sort = "createdAt",
                       direction = Sort.Direction.DESC
               )
               Pageable pageable
       ){

              return ApiResponse.success(
                      productService.getAll(filter, pageable)
              );
       }
}
