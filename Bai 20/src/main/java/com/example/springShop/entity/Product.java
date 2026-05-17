package com.example.springShop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity{

     @Column(nullable = false,length = 255)
     private String name;

     @Column(columnDefinition = "TEXT")
     private String description;

     @Column(name = "material_info",columnDefinition = "TEXT")
     private String materialInfo;

     @Column(columnDefinition = "TEXT")
     private String avatar;

     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "category_id",nullable = false)
     private Category category;

     @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
     private List<ProductSku> skus;

     @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
     private List<ProductImage> images;

}
