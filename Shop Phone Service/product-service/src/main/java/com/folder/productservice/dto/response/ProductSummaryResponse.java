package com.folder.productservice.dto.response;

import com.folder.productservice.enums.ProductStatus;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductSummaryResponse {

       private UUID id;

       private String name;

       private String slug;

       private String thumbnail;

       private Float minPrice;

       private Float maxPrice;

       private ProductStatus status;
}
