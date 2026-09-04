package com.folder.order_service.mapper;

import com.folder.order_service.dto.response.OrderItemResponse;
import com.folder.order_service.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

       @Mapping(target = "id", source = "id")
       @Mapping(target = "variant", source = "variant")
       @Mapping(target = "productName", source = "productName")
       @Mapping(target = "ram", source = "ram")
       @Mapping(target = "storage", source = "storage")
       @Mapping(target = "color", source = "color")
       @Mapping(target = "price", source = "price")
       @Mapping(target = "quantity", source = "quantity")
       OrderItemResponse toResponse(OrderItem orderItem);
}
