package com.folder.order_service.entity;

import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "cart_items",
         uniqueConstraints = {
            @UniqueConstraint(name = "uk_car_variant",
            columnNames = {"cart_id","variant_id"})
         },
        indexes = {
        @Index(name = "idx_cart_item_cart_id", columnList = "cart_id"),
        @Index(name = "idx_cart_item_variant_id", columnList = "variant_id")
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItem extends Serializers.Base {

       @ManyToOne(fetch = FetchType.LAZY)
       @JoinColumn(name = "cart_id",
               nullable = false)
       private Cart cart;

       @Column(name = "variant_id", nullable = false)
       private UUID variantId;

       @Column(nullable = false)
       private Integer quantity;

}
