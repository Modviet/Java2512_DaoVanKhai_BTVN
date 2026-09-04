package com.folder.productservice.dto.request;

import com.folder.productservice.enums.ProductStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ProductFilterRequest {

       private String keyword;

       private UUID brandId;

       private UUID categoryId;

       private Float minPrice;

       private Float maxPrice;

       private ProductStatus status;
}
