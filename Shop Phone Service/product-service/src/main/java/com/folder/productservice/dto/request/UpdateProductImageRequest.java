package com.folder.productservice.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateProductImageRequest {

       private String imageUrl;

       private Boolean isThumbnail;
}
