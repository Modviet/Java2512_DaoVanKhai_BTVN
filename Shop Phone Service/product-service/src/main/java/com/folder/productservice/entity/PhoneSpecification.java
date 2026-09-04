package com.folder.productservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "phone_specifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhoneSpecification extends BaseEntity {

       @OneToOne(fetch = FetchType.LAZY)
       @JoinColumn(name = "product_id", nullable = false, unique = true)
       private Product product;

       @Column(name = "screen_size", nullable = false)
       private String screenSize;

       @Column(nullable = false)
       private String resoulution;

       @Column(name = "refresh_rate", nullable = false)
       private String refreshRate;

       @Column(nullable = false)
       private String chip;

       @Column(name = "operating_system", nullable = false)
       private String operatingSystem;

       @Column(name = "battery_capacity", nullable = false)
       private String batteryCapacity;

       @Column(name = "front_camera")
       private String frontCamera;

       @Column(name = "rear_camera")
       private String rearCamera;
}
