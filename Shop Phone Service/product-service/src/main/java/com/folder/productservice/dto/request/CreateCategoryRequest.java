package com.folder.productservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateCategoryRequest {

      @NotBlank
      @Size(max = 100)
      private String name;

      @NotBlank
      @Size(max = 150)
      private String slug;

      @NotBlank
      private String image;

      private String description;
}
