package com.folder.order_service.controller;

import com.folder.order_service.dto.response.OrderItemResponse;
import com.folder.order_service.exception.common.ApiResponse;
import com.folder.order_service.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/order-items")
@RequiredArgsConstructor
public class OrderItemController {

       private final OrderItemService orderItemService;

       @GetMapping("/order/{orderId}")
       public ResponseEntity<ApiResponse<List<OrderItemResponse>>> getItemsByOrderId(
               @PathVariable UUID orderId
               ){

           log.info("Getting items for order: {}", orderId);

           List<OrderItemResponse> response = orderItemService.getItemByOrderId(orderId);

           return ResponseEntity.ok(ApiResponse.success(response));
       }
}
