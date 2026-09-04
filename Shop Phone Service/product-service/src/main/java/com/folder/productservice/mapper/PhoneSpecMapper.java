package com.folder.productservice.mapper;

import com.folder.productservice.dto.request.CreatePhoneSpecRequest;
import com.folder.productservice.dto.request.UpdatePhoneSpecRequest;
import com.folder.productservice.dto.response.PhoneSpecResponse;
import com.folder.productservice.entity.PhoneSpecification;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface PhoneSpecMapper {

       PhoneSpecification toEntity(CreatePhoneSpecRequest request);

       PhoneSpecResponse toResponse(PhoneSpecification phoneSpec);

       @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
       void update(UpdatePhoneSpecRequest request,
                   @MappingTarget PhoneSpecification phoneSpec);
}
