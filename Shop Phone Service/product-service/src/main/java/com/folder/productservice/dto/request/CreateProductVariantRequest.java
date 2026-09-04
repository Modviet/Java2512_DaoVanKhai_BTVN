package com.folder.productservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateProductVariantRequest {

       @NotNull
       private UUID productId;

       @NotBlank
       private String sku;

       @NotBlank
       private String ram;

       @NotBlank
       private String storage;

       @NotBlank
       private String color;

       @NotNull
       private Float price;

       @NotNull
       @PositiveOrZero
       private Integer stock;
}
