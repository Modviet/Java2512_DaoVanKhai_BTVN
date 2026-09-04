package com.folder.order_service.service;

import com.folder.order_service.dto.response.OrderItemResponse;

import java.util.List;
import java.util.UUID;

public interface OrderItemService {

     List<OrderItemResponse> getItemByOrderId(UUID orderId);
}
