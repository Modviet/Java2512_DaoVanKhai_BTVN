package com.folder.productservice.mapper;

import com.folder.productservice.dto.request.CreateReviewRequest;
import com.folder.productservice.dto.request.UpdateReviewRequest;
import com.folder.productservice.dto.response.ReviewResponse;
import com.folder.productservice.entity.Review;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

       Review toEntity(CreateReviewRequest request);

       ReviewResponse toResponse(Review review);

       @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
       void update(UpdateReviewRequest request,
                   @MappingTarget Review review);
}
