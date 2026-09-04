package com.folder.order_service.controller;

import com.folder.order_service.dto.response.CartResponse;
import com.folder.order_service.exception.common.ApiResponse;
import com.folder.order_service.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/carts")
public class CartController {

      private final CartService cartService;

      @GetMapping("/user/{userId}")
      public ResponseEntity<ApiResponse<CartResponse>> getCartByUserId(
              @PathVariable UUID userId
              ){

          log.info("Getting cart for user:{}", userId);

          CartResponse response = cartService.getCartByUserId(userId);

          return ResponseEntity.ok(ApiResponse.success(response));
      }

      @DeleteMapping("/user/{userId}")
      public ResponseEntity<ApiResponse<Void>> clearCart(
              @PathVariable UUID userId
      ){

          log.info("Clearing cart for user: {}", userId);

          cartService.clearCart(userId);

          return ResponseEntity.ok(ApiResponse.success(null));
      }
}
