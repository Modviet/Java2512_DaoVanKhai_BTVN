package com.folder.productservice.mapper;

import com.folder.productservice.dto.request.CreateProductRequest;
import com.folder.productservice.dto.request.UpdateProductRequest;
import com.folder.productservice.dto.response.ProductResponse;
import com.folder.productservice.dto.response.ProductSummaryResponse;
import com.folder.productservice.entity.Product;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ProductMapper {

       Product toEntity(CreateProductRequest  request);

       ProductResponse toResponse(Product product);

       ProductSummaryResponse toSummaryResponse(Product product);

       @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
       void update(UpdateProductRequest request,
                   @MappingTarget Product product);

}
