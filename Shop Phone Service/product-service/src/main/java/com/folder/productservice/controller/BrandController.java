package com.folder.productservice.controller;

import com.folder.productservice.dto.request.CreateBrandRequest;
import com.folder.productservice.dto.request.UpdateBrandRequest;
import com.folder.productservice.dto.response.ApiResponse;
import com.folder.productservice.dto.response.BrandResponse;
import com.folder.productservice.service.BrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/brands")
@RequiredArgsConstructor
public class BrandController {

       private final BrandService brandService;

       @PostMapping
       public ApiResponse<BrandResponse> create(
               @Valid @RequestBody CreateBrandRequest request) {

             return ApiResponse.<BrandResponse>builder()
                     .result(brandService.create(request))
                     .build();
       }

       @PutMapping("/{id}")
       public ApiResponse<BrandResponse> update(
               @PathVariable UUID id,
               @Valid @RequestBody UpdateBrandRequest request) {

              return ApiResponse.<BrandResponse>builder()
                      .result(brandService.update(id, request))
                      .build();
       }

       @DeleteMapping("/{id}")
       public ApiResponse<Void> delete(
               @PathVariable UUID id){

           brandService.delete(id);

           return ApiResponse.<Void>builder()
                   .success(true)
                   .message("Delete brand successfully")
                   .build();

       }

       @GetMapping("/{id}")
       public ApiResponse<BrandResponse> getById(
               @PathVariable UUID id) {

           return ApiResponse.<BrandResponse>builder()
                   .result(brandService.getById(id))
                   .build();
       }

       @GetMapping
       public ApiResponse<List<BrandResponse>> getAll() {

           return ApiResponse.<List<BrandResponse>>builder()
                   .result(brandService.getAll())
                   .build();
       }
}
