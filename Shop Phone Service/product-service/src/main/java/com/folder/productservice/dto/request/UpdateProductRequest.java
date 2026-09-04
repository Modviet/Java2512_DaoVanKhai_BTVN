package com.folder.productservice.dto.request;

import com.folder.productservice.enums.ProductStatus;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateProductRequest {

        @Size(max = 200)
        private String name;

        @Size(max = 250)
        private String slug;

        private String thumbnail;

        private String description;

        private UUID brandId;

        private UUID categoryId;

        private ProductStatus status;
}
