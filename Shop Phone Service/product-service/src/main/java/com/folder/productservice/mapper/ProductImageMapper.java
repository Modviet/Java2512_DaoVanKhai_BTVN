package com.folder.productservice.mapper;

import com.folder.productservice.dto.request.CreateProductImageRequest;
import com.folder.productservice.dto.request.UpdateProductImageRequest;
import com.folder.productservice.dto.response.ProductImageResponse;
import com.folder.productservice.entity.ProductImage;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ProductImageMapper {

       ProductImage toEntity(CreateProductImageRequest request);

       ProductImageResponse toResponse(ProductImage image);

       @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
       void update(UpdateProductImageRequest request,
                   @MappingTarget ProductImage image);
}
