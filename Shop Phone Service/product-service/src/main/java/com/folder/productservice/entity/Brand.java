package com.folder.productservice.entity;

import com.folder.productservice.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "brands")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Brand extends BaseEntity{

       @Column(nullable = false, unique = true, length = 100)
       private String name;

       @Column(nullable = false, unique = true, length = 150)
       private String slug;

       @Column(length = 500)
       private String logo;

}
