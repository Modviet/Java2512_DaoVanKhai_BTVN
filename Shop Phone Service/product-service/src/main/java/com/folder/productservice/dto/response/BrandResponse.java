package com.folder.productservice.dto.response;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrandResponse {

       private UUID id;

       private String name;

       private String slug;

       private String logo;

}
