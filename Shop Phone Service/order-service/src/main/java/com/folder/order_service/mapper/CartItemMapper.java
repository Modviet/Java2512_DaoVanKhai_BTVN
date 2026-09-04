package com.folder.order_service.mapper;

import com.folder.order_service.dto.response.CartItemResponse;
import com.folder.order_service.entity.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartItemMapper {

      @Mapping(target = "id", source = "id")
      @Mapping(target = "variantId", source = "variantId")
      @Mapping(target = "quantity", source = "quantity")
      CartItemResponse toResponse(CartItem cartItem);
}
