package com.folder.order_service.repository;

import com.folder.order_service.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartItemReponsitory extends JpaRepository<CartItem, UUID> {

       List<CartItem> findByCartId(UUID cartId);

       Optional<CartItem> findByCartIdAndVariantId(
               UUID cartId,
               UUID variantId
       );

       void deleteByCarId(UUID cartId);

       void deleteByCartIdAndVariantId(UUID cartId, UUID variantId);
}
