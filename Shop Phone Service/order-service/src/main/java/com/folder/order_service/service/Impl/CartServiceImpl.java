package com.folder.order_service.service.Impl;

import com.folder.order_service.dto.response.CartResponse;
import com.folder.order_service.entity.Cart;
import com.folder.order_service.exception.ResourceNotFoundException;
import com.folder.order_service.mapper.CartMapper;
import com.folder.order_service.repository.CartRepository;
import com.folder.order_service.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class CartServiceImpl implements CartService {

          private final CartRepository cartRepository;

          private final CartMapper cartMapper;

    @Override
    @Transactional(readOnly = true)
    public CartResponse getCartByUserId(UUID userId) {

        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(()->
                        new ResourceNotFoundException("Cart not found for user: "+ userId));

        return cartMapper.toResponse(cart);
    }

    @Override
    public void clearCart(UUID userId) {

           Cart cart = cartRepository.findByUserId(userId)
                   .orElseThrow(()->
                           new ResourceNotFoundException("Cart not found for user : "+ userId));

           cart.getItems().clear();

           cartRepository.save(cart);
    }
}
