package com.folder.order_service.service.Impl;

import com.folder.order_service.dto.request.cart.AddCartItemRequest;
import com.folder.order_service.dto.request.cart.UpdateCartItemRequest;
import com.folder.order_service.dto.response.CartItemResponse;
import com.folder.order_service.entity.Cart;
import com.folder.order_service.entity.CartItem;
import com.folder.order_service.exception.ResourceNotFoundException;
import com.folder.order_service.mapper.CartItemMapper;
import com.folder.order_service.repository.CartItemReponsitory;
import com.folder.order_service.repository.CartRepository;
import com.folder.order_service.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class CartItemServiceImpl implements CartItemService {

       private final CartRepository cartRepository;

       private final CartItemReponsitory itemReponsitory;

       private final CartItemMapper itemMapper;

    @Override
    public CartItemResponse addItem(UUID userId,
                                    AddCartItemRequest request) {

        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(()-> createCart(userId));

        CartItem cartItem = itemReponsitory.findByCartIdAndVariantId(cart.getId(),
                                                                     request.getVaraintID())
                .orElse(null);

        if (cartItem != null){

            cartItem.setQuantity(
                    cartItem.getQuantity() + request.getQuantity()
            );
        } else {

            cartItem = CartItem.builder()
                    .cart(cart)
                    .variantId(request.getVaraintID())
                    .quantity(request.getQuantity())
                    .build();
        }

        return itemMapper.toResponse(itemReponsitory.save(cartItem));
    }

    @Override
    public CartItemResponse updateItem(UUID userId,
                                       UUID itemId,
                                       UpdateCartItemRequest request) {

            Cart cart = cartRepository.findByUserId(userId)
                    .orElseThrow(()-> new ResourceNotFoundException("Cart not found"));

            CartItem cartItem = itemReponsitory.findById(itemId)
                    .orElseThrow(()-> new ResourceNotFoundException("Cart item not found"));

            if(!cartItem.getCart().getId()
                    .equals(cart.getId())) {

                throw new ResourceNotFoundException("Cart item does not belong to this cart");
            }

            cartItem.setQuantity(request.getQuantity());

            return itemMapper.toResponse(itemReponsitory.save(cartItem));
    }

    @Override
    public void removeItem(UUID userId,
                           UUID itemId) {

           Cart cart = cartRepository.findByUserId(userId)
                   .orElseThrow(()->
                           new ResourceNotFoundException("Cart not found"));

           CartItem cartItem = itemReponsitory.findById(itemId)
                   .orElseThrow(()-> new ResourceNotFoundException("Cart item not found"));

           if (!cartItem.getCart().getId()
                   .equals(cart.getId())) {

               throw new ResourceNotFoundException("Cart item does not belong to this cart");

           }

           itemReponsitory.delete(cartItem);
    }


    private Cart createCart(UUID userId){

        Cart cart = Cart.builder()
                .userId(userId)
                .build();

        return cartRepository.save(cart);
    }
}
