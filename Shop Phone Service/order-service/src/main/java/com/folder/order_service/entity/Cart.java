package com.folder.order_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "carts",
         indexes = {
            @Index(name = "idx_cart_user_id", columnList = "user_id")
         })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart extends BaseEntity{

      @Column(name = "user_id", nullable = false)
      private UUID userId;

      @OneToMany(
              mappedBy = "cart",
              cascade = CascadeType.ALL,
              orphanRemoval = true
      )
      @Builder.Default
      private List<CartItem> items = new ArrayList<>();
}
