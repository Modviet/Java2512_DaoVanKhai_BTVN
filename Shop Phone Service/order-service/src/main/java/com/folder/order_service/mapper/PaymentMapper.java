package com.folder.order_service.mapper;

import com.folder.order_service.dto.response.PaymentResponse;
import com.folder.order_service.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

       @Mapping(target = "id", source = "id")
       @Mapping(target = "orderId", source = "order.id")
       @Mapping(target = "paymentMethod", source = "paymentMethod")
       @Mapping(target = "paymentStatus", source = "paymentStatus")
       @Mapping(target = "amount", source = "amount")
       @Mapping(target = "transactionCode", source = "transactionCode")
       @Mapping(target = "paidAt", source = "paidAt")
       PaymentResponse toResponse(Payment payment);
}
