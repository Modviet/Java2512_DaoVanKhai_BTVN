package com.folder.productservice.controller;

import com.folder.productservice.dto.request.CreatePhoneSpecRequest;
import com.folder.productservice.dto.request.UpdatePhoneSpecRequest;
import com.folder.productservice.dto.response.ApiResponse;
import com.folder.productservice.dto.response.PhoneSpecResponse;
import com.folder.productservice.service.PhoneSpecService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/phone-specs")
@RequiredArgsConstructor
public class PhoneSpecController {

      private final PhoneSpecService phoneSpecService;

      @PostMapping
      public ApiResponse<PhoneSpecResponse> create(
              @Valid @RequestBody CreatePhoneSpecRequest request){

          return ApiResponse.success(
                  phoneSpecService.create(request)
          );
      }

      @PutMapping("/{productId}")
      public ApiResponse<PhoneSpecResponse> update(
              @PathVariable UUID productId,
              @Valid @RequestBody UpdatePhoneSpecRequest request) {

          return ApiResponse.success(
                  phoneSpecService.update(productId, request)
          );
      }

      @GetMapping("/product/{productId}")
      public ApiResponse<PhoneSpecResponse> getByProductId(
              @PathVariable UUID productId) {

          return ApiResponse.success(
                  phoneSpecService.getByProductId(productId)
          );
      }
}
