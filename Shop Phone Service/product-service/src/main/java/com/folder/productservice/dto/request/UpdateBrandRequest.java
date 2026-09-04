package com.folder.productservice.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateBrandRequest {

       @Size(max = 100)
       private String name;

       @Size(max = 150)
       private String slug;

       private String logo;

}
