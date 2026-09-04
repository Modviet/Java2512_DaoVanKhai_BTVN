package com.folder.productservice.dto.response;

import com.folder.productservice.enums.ProductStatus;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductVariantResponse {

       private UUID id;

       private String sku;

       private String ram;

       private String storage;

       private String color;

       private Float price;

       private Integer stock;

       private ProductStatus status;
}
