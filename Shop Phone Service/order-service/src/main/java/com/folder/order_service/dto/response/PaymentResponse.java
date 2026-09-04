package com.folder.order_service.dto.response;

import com.folder.order_service.enums.PaymentMethod;
import com.folder.order_service.enums.PaymentStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse {

        private UUID id;

        private UUID orderId;

        private PaymentMethod paymentMethod;

        private PaymentStatus paymentStatus;

        private Float amount;

        private String transactionCode;

        private LocalDateTime paidAt;
}
