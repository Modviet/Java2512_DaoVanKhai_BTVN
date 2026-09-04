package com.folder.order_service.service.Impl;

import com.folder.order_service.dto.request.order.CreateOrderRequest;
import com.folder.order_service.dto.request.order.OrderFilter;
import com.folder.order_service.dto.request.order.UpdateOrderStatusRequest;
import com.folder.order_service.dto.response.OrderDetailResponse;
import com.folder.order_service.dto.response.OrderResponse;
import com.folder.order_service.entity.Order;
import com.folder.order_service.enums.OrderStatus;
import com.folder.order_service.exception.BusinessException;
import com.folder.order_service.exception.ResourceNotFoundException;
import com.folder.order_service.mapper.OrderMapper;
import com.folder.order_service.repository.OrderRepository;
import com.folder.order_service.service.OrderService;
import com.folder.order_service.specification.OrderSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

      private final OrderRepository orderRepository;

      private final OrderMapper orderMapper;

    @Override
    public OrderDetailResponse createOrder(CreateOrderRequest request) {

        Order order = Order.builder()
                .orderCode(generateOrderCode())
                .userId(request.getUserId())
                .receiverName(request.getReceiverName())
                .receiverPhone(request.getReceiverPhone())
                .province(request.getProvince())
                .district(request.getDistrict())
                .ward(request.getWard())
                .detailAddress(request.getDetailAddress())
                .totalPrice(0F)
                .status(OrderStatus.PENDING)
                .build();

        /*
         * TODO:
         *
         * 1. Lấy Cart của user
         * 2. Lấy các CartItem
         * 3. Gọi Product-Service lấy thông tin Variant
         * 4. Tạo OrderItem snapshot
         * 5. Tính totalPrice
         * 6. Tạo Payment
         * 7. Clear Cart
         */

        Order savedOrder = orderRepository.save(order);

        return orderMapper.toDetailResponse(savedOrder);
    }

    @Override
    @Transactional(readOnly = true)
    public OrderDetailResponse getOrderById(UUID orderId) {

           Order order = orderRepository.findById(orderId)
                   .orElseThrow(()->
                           new ResourceNotFoundException("Order not found : "+ orderId));

           return orderMapper.toDetailResponse(order);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<OrderResponse> getOrders(OrderFilter filter,
                                         Pageable pageable) {

            return orderRepository.findAll(
                    OrderSpecification.filter(filter),
                    pageable
            )
                    .map(orderMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<OrderResponse> getOrderByUser(UUID userId,
                                              Pageable pageable) {

           OrderFilter filter = OrderFilter.builder()
                                    .userId(userId)
                                    .build();

           return orderRepository.findAll(OrderSpecification.filter(filter),
                   pageable
           ).map(orderMapper::toResponse);
    }

    @Override
    public OrderResponse updateStatus(UUID orderId,
                                      UpdateOrderStatusRequest request) {

            Order order = orderRepository.findById(orderId)
                    .orElseThrow(()-> new ResourceNotFoundException("Order not found : " + orderId));

            validateStatusTransition(order.getStatus(), request.getStatus());

            order.setStatus(request.getStatus());

            return orderMapper.toResponse(orderRepository.save(order));
    }

    @Override
    public void cancelOrder(UUID orderId) {

           Order order = orderRepository.findById(orderId)
                   .orElseThrow(()->
                           new ResourceNotFoundException("Order not found : "+ orderId));

           if (order.getStatus()  == OrderStatus.DELIVERED){

               throw new BusinessException("Delivered order cannot be cancelled");
           }

           if (order.getStatus() == OrderStatus.CANCELLED){

               throw new BusinessException("Order is already cancelled");

           }

           order.setStatus(OrderStatus.CANCELLED);

           orderRepository.save(order);
    }

    private void validateStatusTransition(OrderStatus current,
                                          OrderStatus next){

        if (current == OrderStatus.CANCELLED){

            throw new BusinessException("Cancelled order cannot change status");
        }

        if (current == OrderStatus.DELIVERED){

            throw new BusinessException("Delivered order cannot change status");
        }

        if (current == next){

            throw new BusinessException("Order is already in this status");
        }

        boolean valid =
                switch (current){

                    case PENDING ->
                       next == OrderStatus.CONFIRMED
                               || next == OrderStatus.CANCELLED;

                    case CONFIRMED ->
                        next == OrderStatus.PROCESSING
                                || next == OrderStatus.CANCELLED;


                    case PROCESSING ->
                        next == OrderStatus.SHIPPING
                                 || next == OrderStatus.CANCELLED;

                    case SHIPPING ->
                        next == OrderStatus.DELIVERED;

                    case DELIVERED, CANCELLED -> false;
                };

        if (!valid){

            throw new BusinessException(
                    "Invaild order status transition : "
                        + current
                        + " -> "
                        + next
            );
        }
    }

    private String generateOrderCode(){

        return "ORD-"+ System.currentTimeMillis();
    }
}
