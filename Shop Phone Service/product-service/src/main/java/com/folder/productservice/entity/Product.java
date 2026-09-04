package com.folder.productservice.entity;

import com.folder.productservice.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product extends BaseEntity{

       @Column(nullable = false, length = 200)
       private String name;

       @Column(nullable = false, unique = true, length = 250)
       private String slug;

       @Column(nullable = false, length = 500)
       private String thumbnail;

       @Column(columnDefinition = "TEXT")
       private String description;

       @ManyToOne(fetch = FetchType.LAZY)
       @JoinColumn(name = "brand_id", nullable = false)
       private Brand brand;

       @ManyToOne(fetch = FetchType.LAZY)
       @JoinColumn(name = "category_id", nullable = false)
       private Category category;

       @OneToMany(
               mappedBy = "product",
               cascade = CascadeType.ALL,
               orphanRemoval = true
       )
       private List<Review> reviews = new ArrayList<>();

       @OneToMany(
               mappedBy = "product",
               cascade = CascadeType.ALL,
               orphanRemoval = true
       )
       private List<ProductVariant> variants = new ArrayList<>();


       @Enumerated(EnumType.STRING)
       @Column(nullable = false)
       private ProductStatus status;

}
