package com.folder.productservice.mapper;

import com.folder.productservice.dto.request.CreateProductVariantRequest;
import com.folder.productservice.dto.request.UpdateProductVariantRequest;
import com.folder.productservice.dto.response.ProductVariantResponse;
import com.folder.productservice.entity.ProductVariant;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ProductVariantMapper {

       ProductVariant toEntity(CreateProductVariantRequest request);

       ProductVariantResponse toResponse(ProductVariant variant);

       @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
       void update(UpdateProductVariantRequest request,
                   @MappingTarget ProductVariant variant);
}
