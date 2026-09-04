package com.folder.productservice.dto.response;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryResponse {

       private UUID id;

       private String name;

       private String slug;

       private String image;

       private String description;
}
