package com.folder.order_service.dto.request.order;

import com.folder.order_service.enums.OrderStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderFilter {

       private UUID userId;

       private String orderCode;

       private OrderStatus status;

       private Float minPrice;

       private Float maxPrice;

       private LocalDateTime fromDate;

       private LocalDateTime toDate;
}
