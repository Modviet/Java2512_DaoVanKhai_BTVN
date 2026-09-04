package com.folder.productservice.dto.response;

import com.folder.productservice.enums.ProductStatus;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {

       private UUID id;

       private String name;

       private String slug;

       private String thumbnail;

       private String description;

       private BrandResponse brand;

       private CategoryResponse category;

       private ProductStatus status;

       private PhoneSpecResponse phoneSpec;

       private List<ProductVariantResponse> variants;

       private List<ProductImageResponse> images;

       private List<ReviewResponse> reviews;
}
