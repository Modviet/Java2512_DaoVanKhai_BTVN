package com.folder.order_service.entity;

import com.folder.order_service.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders",
             indexes = {
                  @Index(name = "idx_order_user_id", columnList = "user_id"),
                  @Index(name = "idx_order_status", columnList = "status"),
                  @Index(name = "idx_order_created_at", columnList = "created_at")
             })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order extends BaseEntity{

       @Column(name = "order_code",
                    nullable = false,
                    unique = true,
                    length = 50)
       private String orderCode;

       @Column(name = "user_id", nullable = false)
       private UUID userId;

       @Column(name = "receiver_name",
               nullable = false,
               length = 100)
       private String receiverName;

       @Column(name = "receiver_phone",
               nullable = false,
               length = 20)
       private String receiverPhone;

       @Column(nullable = false, length = 100)
       private String province;

       @Column(nullable = false, length = 100)
       private String district;

       @Column(nullable = false, length = 100)
       private String ward;

       @Column(name = "detail_address",
               nullable = false,
               length = 255)
       private String detailAddress;

       @Column(name = "total_price",
               nullable = false)
       private Float totalPrice;

       @Enumerated(EnumType.STRING)
       @Column(nullable = false, length = 20)
       private OrderStatus status;

       @OneToMany(mappedBy = "order",
                  cascade = CascadeType.ALL,
                   orphanRemoval = true)
       @Builder.Default
       private List<OrderItem> items = new ArrayList<>();

       @OneToMany(mappedBy = "order",
                    cascade = CascadeType.ALL,
                   orphanRemoval = true)
       @Builder.Default
       private List<Payment> payments = new ArrayList<>();
}
