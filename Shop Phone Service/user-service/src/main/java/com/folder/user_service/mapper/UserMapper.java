package com.folder.user_service.mapper;

import com.folder.user_service.dto.request.CreateUserRequest;
import com.folder.user_service.dto.request.UpdateProfileRequest;
import com.folder.user_service.dto.request.UpdateUserRequest;
import com.folder.user_service.dto.response.UserResponse;
import com.folder.user_service.dto.response.UserSummaryResponse;
import com.folder.user_service.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(CreateUserRequest request);

    UserResponse toResponse(User user);

    UserSummaryResponse toSummaryResponse(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "keycloakId", ignore = true)
    @Mapping(target = "username", ignore = true)
    void updateUser(UpdateUserRequest request, @MappingTarget User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "keycloakId", ignore = true)
    @Mapping(target = "username", ignore = true)
    void updateProfile(UpdateProfileRequest request, @MappingTarget User user);
}
