package com.folder.productservice.entity;

import com.folder.productservice.enums.ReviewStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "reviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review extends BaseEntity{

       @ManyToOne(fetch = FetchType.LAZY)
       @JoinColumn(name = "product_id", nullable = false)
       private Product product;

       @Column(name = "user_id", nullable = false)
       private UUID userId;

       @Column(nullable = false)
       private Integer rating;

       @Column(columnDefinition = "TEXT")
       private String comment;

       @Enumerated(EnumType.STRING)
       @Column(nullable = false)
       private ReviewStatus status;
}
