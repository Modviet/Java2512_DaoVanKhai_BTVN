package com.folder.productservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateProductImageRequest {

       @NotNull
       private UUID productId;

       @NotBlank
       private String imageUrl;

       @NotNull
       private Boolean IsThumbnail;
}
