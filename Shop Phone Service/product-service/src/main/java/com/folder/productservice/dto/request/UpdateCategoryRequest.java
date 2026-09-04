package com.folder.productservice.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateCategoryRequest {

       @Size(max = 100)
       private String name;

       @Size(max = 150)
       private String slug;

       private String image;

       private String description;
}
