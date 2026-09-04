package com.folder.order_service.service;

import com.folder.order_service.dto.response.CartResponse;

import java.util.UUID;

public interface CartService {

       CartResponse getCartByUserId(UUID userId);

       void clearCart(UUID userId);
}
