package com.folder.productservice.dto.response;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductImageResponse {

       private UUID id;

       private String imageUrl;

       private Boolean isThumbnail;
}
