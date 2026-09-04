package com.folder.order_service.dto.response;

import com.folder.order_service.enums.OrderStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailResponse {

       private UUID id;

       private String orderCode;

       private UUID userId;

       private String receiverName;

       private String receiverPhone;

       private String province;

       private String district;

       private String ward;

       private String detailAddress;

       private Float totalPrice;

       private OrderStatus status;

       private List<OrderItemResponse> items;

       private List<PaymentResponse> payments;

       private LocalDateTime createdAt;

       private LocalDateTime updatedAt;
}
