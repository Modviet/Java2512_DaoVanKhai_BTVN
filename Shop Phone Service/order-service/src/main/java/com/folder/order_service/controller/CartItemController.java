package com.folder.order_service.controller;

import com.folder.order_service.dto.request.cart.AddCartItemRequest;
import com.folder.order_service.dto.request.cart.UpdateCartItemRequest;
import com.folder.order_service.dto.response.CartItemResponse;
import com.folder.order_service.exception.common.ApiResponse;
import com.folder.order_service.service.CartItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/cart-items")
@RestController
public class CartItemController {

    private final CartItemService cartItemService;

    @PostMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<CartItemResponse>> addItem(
            @PathVariable UUID userId,
            @Valid @RequestBody AddCartItemRequest request
    ) {

        log.info("Adding variant {} to cart of user {}",
                request.getVaraintID(),
                userId);

        CartItemResponse response = cartItemService.addItem(userId, request);

        return ResponseEntity.ok(ApiResponse.success(response));
    }

     @PutMapping("/{itemId}/user/{userId}")
     public ResponseEntity<ApiResponse<CartItemResponse>> updateItem(
             @PathVariable UUID userId,
             @PathVariable UUID itemId,
             @Valid @RequestBody UpdateCartItemRequest request
             ){

        log.info("Updating cart item {} for user {}",
                itemId, userId);

        CartItemResponse response = cartItemService.updateItem(userId, itemId, request);

        return ResponseEntity.ok(ApiResponse.success(response));
     }

     @DeleteMapping("/{itemId}/user/{userId}")
     public ResponseEntity<ApiResponse<Void>> removeItem(
             @PathVariable UUID userId,
             @PathVariable UUID itemId
     ){

        log.info("Removing cart item {} for user {}",
                itemId, userId);

        cartItemService.removeItem(userId, itemId);

        return ResponseEntity.ok(ApiResponse.success(null));
     }
}
