package com.folder.order_service.controller;

import com.folder.order_service.dto.request.order.CreateOrderRequest;
import com.folder.order_service.dto.request.order.OrderFilter;
import com.folder.order_service.dto.request.order.UpdateOrderStatusRequest;
import com.folder.order_service.dto.response.OrderDetailResponse;
import com.folder.order_service.dto.response.OrderResponse;
import com.folder.order_service.exception.common.ApiResponse;
import com.folder.order_service.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

      private final OrderService orderService;

      @PostMapping
      public ResponseEntity<ApiResponse<OrderDetailResponse>> createOrder(
              @Valid @RequestBody CreateOrderRequest request
              ){

          log.info("Creating new order");

          OrderDetailResponse response = orderService.createOrder(request);

          return ResponseEntity.ok(ApiResponse.success(response));
      }

      @GetMapping("/{orderId}")
      public ResponseEntity<ApiResponse<OrderDetailResponse>> getOrderById(
              @PathVariable UUID orderId
              ){

          log.info("Getting order :{} ", orderId);

          OrderDetailResponse response = orderService.getOrderById(orderId);

          return ResponseEntity.ok(ApiResponse.success(response));
      }

      @GetMapping
      public ResponseEntity<ApiResponse<Page<OrderResponse>>> getOrders(
              @ModelAttribute OrderFilter filter,
              @PageableDefault(
                      size = 20,
                      sort = "createdAt",
                      direction = Sort.Direction.DESC
              )
              Pageable pageable
              ){

             log.info("Getting orders with fillter : {}", filter);

             Page<OrderResponse> response = orderService.getOrders(filter, pageable);

             return ResponseEntity.ok(ApiResponse.success(response));
      }

      @GetMapping("/user/{userId}")
      public ResponseEntity<ApiResponse<Page<OrderResponse>>> getOrdersByUser(
              @PathVariable UUID userId,
              @PageableDefault(
                      size = 20,
                      sort = "createdAt",
                      direction = Sort.Direction.DESC
              )
             Pageable pageable
      ){

          log.info("Getting orders for user :{} ", userId);

          Page<OrderResponse> response = orderService.getOrderByUser(userId, pageable);

          return ResponseEntity.ok(ApiResponse.success(response));
      }

      @PatchMapping("/{orderId}/status")
      public ResponseEntity<ApiResponse<OrderResponse>> updateStatus(
              @PathVariable UUID orderId,
              @Valid @RequestBody UpdateOrderStatusRequest request
              ){

          log.info("Updating order {} status to {}", orderId,
                  request.getStatus());

          OrderResponse response = orderService.updateStatus(orderId, request);

          return ResponseEntity.ok(ApiResponse.success(response));
      }

      @PatchMapping("/{orderId}/cancel")
      public ResponseEntity<ApiResponse<Void>> cancelOrder(
              @PathVariable UUID orderId
      ){

          log.info("Cancelling order : {}", orderId);

          orderService.cancelOrder(orderId);

          return ResponseEntity.ok(ApiResponse.success(null));
      }

}
