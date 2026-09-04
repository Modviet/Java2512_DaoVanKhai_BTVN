package com.folder.productservice.dto.request;

import com.folder.productservice.enums.ReviewStatus;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateReviewRequest {

       @Min(1)
       @Max(5)
       private Integer rating;

       private String comment;

       private ReviewStatus status;
}
