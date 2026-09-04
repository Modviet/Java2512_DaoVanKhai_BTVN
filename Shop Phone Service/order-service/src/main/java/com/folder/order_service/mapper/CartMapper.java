package com.folder.order_service.mapper;

import com.folder.order_service.dto.response.CartResponse;
import com.folder.order_service.entity.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
         uses = CartItemMapper.class)
public interface CartMapper {

        @Mapping(target = "id", source = "id")
        @Mapping(target = "userId", source = "userId")
        @Mapping(target = "items", source = "items")
        CartResponse toResponse(Cart cart);
}
