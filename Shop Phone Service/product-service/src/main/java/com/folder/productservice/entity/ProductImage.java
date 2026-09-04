package com.folder.productservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_images")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductImage extends BaseEntity{

       @ManyToOne(fetch = FetchType.LAZY)
       @JoinColumn(name = "product_id", nullable = false)
       private Product product;

       @Column(name = "image_url", nullable = false, columnDefinition = "TEXT")
       private String imageUrl;

       @Column(name = "is_thumbnail", nullable = false)
       private Boolean isThumbnail;
}
