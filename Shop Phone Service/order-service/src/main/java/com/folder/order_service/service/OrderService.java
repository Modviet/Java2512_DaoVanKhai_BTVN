package com.folder.order_service.service;

import com.folder.order_service.dto.request.order.CreateOrderRequest;
import com.folder.order_service.dto.request.order.OrderFilter;
import com.folder.order_service.dto.request.order.UpdateOrderStatusRequest;
import com.folder.order_service.dto.response.OrderDetailResponse;
import com.folder.order_service.dto.response.OrderResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface OrderService {

      OrderDetailResponse createOrder(CreateOrderRequest request);

      OrderDetailResponse getOrderById(UUID orderId);

      Page<OrderResponse> getOrders(OrderFilter filter,
                                    Pageable pageable);

      Page<OrderResponse> getOrderByUser(UUID userId,
                                         Pageable pageable);

      OrderResponse updateStatus(UUID orderId,
                                 UpdateOrderStatusRequest request);

      void cancelOrder(UUID orderId);
}
