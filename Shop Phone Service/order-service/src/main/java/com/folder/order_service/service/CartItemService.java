package com.folder.order_service.service;

import com.folder.order_service.dto.request.cart.AddCartItemRequest;
import com.folder.order_service.dto.request.cart.UpdateCartItemRequest;
import com.folder.order_service.dto.response.CartItemResponse;

import java.util.UUID;

public interface CartItemService {

      CartItemResponse addItem(UUID userId,
                               AddCartItemRequest request);

      CartItemResponse updateItem(UUID userId,
                                  UUID itemId,
                                  UpdateCartItemRequest request);

      void removeItem(UUID userId,
                      UUID itemId);
}
