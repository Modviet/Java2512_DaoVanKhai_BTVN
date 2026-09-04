package com.folder.order_service.dto.request.payment;

import com.folder.order_service.enums.PaymentStatus;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePaymentStatusRequest {

       @NotNull(message = "Payment status must not be null")
       private PaymentStatus paymentStatus;

       private String transactionCode;
}
