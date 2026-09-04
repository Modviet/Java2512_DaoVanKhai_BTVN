package com.folder.order_service.mapper;

import com.folder.order_service.dto.response.OrderDetailResponse;
import com.folder.order_service.dto.response.OrderResponse;
import com.folder.order_service.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
            uses = {
                  OrderItemMapper.class,
                  PaymentMapper.class
            })
public interface OrderMapper {

      @Mapping(target = "id", source = "id")
      @Mapping(target = "orderCode", source = "orderCode")
      @Mapping(target = "userId", source = "userId")
      @Mapping(target = "receiverName", source = "receiverName")
      @Mapping(target = "receiverPhone", source = "receiverPhone")
      @Mapping(target = "province", source = "province")
      @Mapping(target = "district", source = "district")
      @Mapping(target = "ward", source = "ward")
      @Mapping(target = "detailAddress", source = "detailAddress")
      @Mapping(target = "totalPrice", source = "totalPrice")
      @Mapping(target = "status", source = "status")
      @Mapping(target = "createdAt", source = "createdAt")
      @Mapping(target = "updatedAt", source = "updatedAt")
      OrderResponse toResponse(Order order);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "orderCode", source = "orderCode")
    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "receiverName", source = "receiverName")
    @Mapping(target = "receiverPhone", source = "receiverPhone")
    @Mapping(target = "province", source = "province")
    @Mapping(target = "district", source = "district")
    @Mapping(target = "ward", source = "ward")
    @Mapping(target = "detailAddress", source = "detailAddress")
    @Mapping(target = "totalPrice", source = "totalPrice")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "items", source = "items")
    @Mapping(target = "payments", source = "payments")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    OrderDetailResponse toDetailResponse(Order order);
}
