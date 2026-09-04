package com.folder.productservice.entity;

import com.folder.productservice.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_variants")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductVariant extends BaseEntity{

       @ManyToOne(fetch = FetchType.LAZY)
       @JoinColumn(name = "product_id", nullable = false)
       private Product product;

       @Column(nullable = false, unique = true, length = 100)
       private String sku;

       @Column(nullable = false, length = 50)
       private String ram;

       @Column(nullable = false, length = 50)
       private String storage;

       @Column(nullable = false, length = 50)
       private String color;

       @Column(nullable = false)
       private Float price;

       @Column(nullable = false)
       private Integer stock;

       @Enumerated(EnumType.STRING)
       @Column(nullable = false)
       private ProductStatus status;
}
