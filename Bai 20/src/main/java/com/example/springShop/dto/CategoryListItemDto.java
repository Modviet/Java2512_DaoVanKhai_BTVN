package com.example.springShop.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryListItemDto {

    private Integer id;
    private String name;
    private Integer parentId;
    private String parentName;
    private LocalDateTime createdAt;
    private String createdBy;
 }
