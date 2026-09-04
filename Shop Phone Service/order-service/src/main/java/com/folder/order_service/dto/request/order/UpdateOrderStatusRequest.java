package com.folder.order_service.dto.request.order;

import com.folder.order_service.enums.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrderStatusRequest {

       @NotNull(message = "Order status must not be empty")
       private OrderStatus status;
}
