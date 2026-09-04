package com.folder.order_service.service.Impl;

import com.folder.order_service.dto.response.OrderItemResponse;
import com.folder.order_service.exception.ResourceNotFoundException;
import com.folder.order_service.mapper.OrderItemMapper;
import com.folder.order_service.repository.OrderItemRepository;
import com.folder.order_service.repository.OrderRepository;
import com.folder.order_service.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderItemServiceImpl implements OrderItemService {

      private final OrderRepository orderRepository;

      private final OrderItemRepository orderItemRepository;

      private final OrderItemMapper orderItemMapper;

    @Override
    public List<OrderItemResponse> getItemByOrderId(UUID orderId) {

        if (!orderRepository.existsById(orderId)){

            throw new ResourceNotFoundException("Order not found "+ orderId);
        }

        return orderItemRepository.findByOrderId(orderId)
                .stream()
                .map(orderItemMapper::toResponse)
                .toList();
    }
}
