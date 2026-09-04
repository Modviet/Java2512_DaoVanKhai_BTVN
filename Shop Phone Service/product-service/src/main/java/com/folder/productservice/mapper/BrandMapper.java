package com.folder.productservice.mapper;

import com.folder.productservice.dto.request.CreateBrandRequest;
import com.folder.productservice.dto.request.UpdateBrandRequest;
import com.folder.productservice.dto.response.BrandResponse;
import com.folder.productservice.entity.Brand;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface BrandMapper {

       Brand toEntity(CreateBrandRequest request);

       BrandResponse toResponse(Brand brand);

       @BeanMapping(nullValuePropertyMappingStrategy =  NullValuePropertyMappingStrategy.IGNORE)
       void update(UpdateBrandRequest request,
                   @MappingTarget Brand brand);
}
