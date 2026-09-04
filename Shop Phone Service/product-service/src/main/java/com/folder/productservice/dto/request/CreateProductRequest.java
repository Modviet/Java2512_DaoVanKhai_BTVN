package com.folder.productservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateProductRequest {

       @NotBlank
       @Size(max = 200)
       private String name;

       @NotBlank
       @Size(max = 250)
       private String slug;

       @NotBlank
       private String thumbnail;

       private String description;

       @NotNull
       private UUID brandId;

       @NotNull
       private UUID categoryId;
}
