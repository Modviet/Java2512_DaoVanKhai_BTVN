package com.folder.productservice.dto.request;

import com.folder.productservice.enums.ProductStatus;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateProductVariantRequest {

       private String sku;

       private String ram;

       private String storage;

       private String color;

       private Float price;

       @PositiveOrZero
       private Integer stock;

       private ProductStatus status;
}
