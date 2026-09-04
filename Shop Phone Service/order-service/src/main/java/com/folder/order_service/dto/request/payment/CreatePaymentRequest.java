package com.folder.order_service.dto.request.payment;

import com.folder.order_service.enums.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentRequest {

      @NotNull(message = "Order ID must not be empty")
       private UUID orderId;

      @NotNull(message = "Payment method must not be null")
      private PaymentMethod paymentMethod;
}
