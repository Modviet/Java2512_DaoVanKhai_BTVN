package com.example.springShop.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryTreeDto {

    private Integer id;
    private String name;
    private Integer parentId;
    private String parentName;
    private LocalDateTime createdAt;
    private String createdBy;
    private int depth;

    @Builder.Default
    private List<CategoryTreeDto> children = new ArrayList<>();
}
