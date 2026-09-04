package com.folder.order_service.entity;

import com.folder.order_service.enums.PaymentMethod;
import com.folder.order_service.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "payments",
         indexes = {
          @Index(name = "idx_payment_order_id",
                   columnList = "order_id"),
          @Index(name = "idx_payment_transaction_code",
                   columnList = "transaction_code" )
         })
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment extends BaseEntity{


        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "order_id",
                nullable = false)
        private Order order;

        @Enumerated(EnumType.STRING)
        @Column(name = "payment_method",
                nullable = false ,
                length = 30)
        private PaymentMethod paymentMethod;

        @Enumerated(EnumType.STRING)
        @Column(name = "payment_status",
                 length = 20,
                 nullable = false)
        private PaymentStatus paymentStatus;

        @Column(nullable = false)
        private Float amount;

        @Column(name = "transaction_code",
                 length = 100)
        private String transactionCode;

        @Column(name = "paid_at")
        private LocalDateTime paidAt;
}
