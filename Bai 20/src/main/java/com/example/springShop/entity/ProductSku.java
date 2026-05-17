package com.example.springShop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "product_skus")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductSku extends BaseEntity{

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "product_id",nullable = false)
        private Product product;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "color_id",nullable = false)
        private Color color;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "size_id",nullable = false)
        private Size size;

        @Column(name = "sku_code",unique = true,length = 100)
        private String skuCode;

        @Column(name = "original_price",nullable = false,precision = 15,scale = 2)
        private BigDecimal originalPrice;

        @Column(name = "sale_price",precision = 15,scale = 2)
        private BigDecimal salePrice;

        @Column(name = "stock_quantity")
        @Builder.Default
        private Integer stockQuantity = 0;
}
