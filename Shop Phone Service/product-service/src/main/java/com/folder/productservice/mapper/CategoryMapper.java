package com.folder.productservice.mapper;

import com.folder.productservice.dto.request.CreateCategoryRequest;
import com.folder.productservice.dto.request.UpdateCategoryRequest;
import com.folder.productservice.dto.response.CategoryResponse;
import com.folder.productservice.entity.Category;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

       Category toEntity(CreateCategoryRequest request);

       CategoryResponse toResponse(Category category);

       @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
       void update(UpdateCategoryRequest request,
                   @MappingTarget Category category);
}
