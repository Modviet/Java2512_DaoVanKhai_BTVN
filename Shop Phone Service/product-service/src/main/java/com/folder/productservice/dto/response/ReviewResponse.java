package com.folder.productservice.dto.response;

import com.folder.productservice.enums.ReviewStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewResponse {

       private UUID id;

       private UUID userId;

       private Integer rating;

       private String comment;

       private ReviewStatus status;

       private LocalDateTime createdAt;
}
