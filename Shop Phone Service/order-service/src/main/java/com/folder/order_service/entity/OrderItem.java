package com.folder.order_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "order_items",
         indexes = {
           @Index(name = "idx_order_item_order_id",
           columnList = "order_id")
         })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem extends BaseEntity{

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "order_id",
                nullable = false)
        private Order order;

        @Column(name = "variant",
                nullable = false,
                length = 100)
        private String variant;

        @Column(name = "product_name",
                nullable = false,
                length = 200)
        private String productName;

        @Column(length = 50)
        private String ram;

        @Column(length = 50)
        private String storage;

        @Column(length = 50)
        private String color;

        @Column(nullable = false)
        private Float price;

        @Column(nullable = false)
        private Integer quantity;
        
}
