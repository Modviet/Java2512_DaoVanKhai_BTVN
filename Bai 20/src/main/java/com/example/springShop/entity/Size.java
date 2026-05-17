package com.example.springShop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "sizes")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Size extends BaseEntity{

    @Column(name = "size_code",nullable = false,length = 20)
    private String sizeCode;
}
