package com.folder.user_service.mapper;

import com.folder.user_service.dto.request.CreateUserRequest;
import com.folder.user_service.dto.request.UpdateProfileRequest;
import com.folder.user_service.dto.request.UpdateUserRequest;
import com.folder.user_service.dto.response.UserResponse;
import com.folder.user_service.dto.response.UserSummaryResponse;
import com.folder.user_service.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-08-01T15:09:22+0700",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.9 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntity(CreateUserRequest request) {
        if ( request == null ) {
            return null;
        }

        User user = new User();

        user.setUsername( request.getUsername() );
        user.setEmail( request.getEmail() );
        user.setPhone( request.getPhone() );
        user.setAvatar( request.getAvatar() );
        user.setStatus( request.getStatus() );

        return user;
    }

    @Override
    public UserResponse toResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse userResponse = new UserResponse();

        userResponse.setId( user.getId() );
        userResponse.setUsername( user.getUsername() );
        userResponse.setEmail( user.getEmail() );
        userResponse.setPhone( user.getPhone() );
        userResponse.setAvatar( user.getAvatar() );
        userResponse.setStatus( user.getStatus() );
        userResponse.setCreatedAt( user.getCreatedAt() );
        userResponse.setUpdatedAt( user.getUpdatedAt() );

        return userResponse;
    }

    @Override
    public UserSummaryResponse toSummaryResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserSummaryResponse userSummaryResponse = new UserSummaryResponse();

        userSummaryResponse.setId( user.getId() );
        userSummaryResponse.setUsername( user.getUsername() );
        userSummaryResponse.setStatus( user.getStatus() );

        return userSummaryResponse;
    }

    @Override
    public void updateUser(UpdateUserRequest request, User user) {
        if ( request == null ) {
            return;
        }

        if ( request.getEmail() != null ) {
            user.setEmail( request.getEmail() );
        }
        if ( request.getPhone() != null ) {
            user.setPhone( request.getPhone() );
        }
        if ( request.getAvatar() != null ) {
            user.setAvatar( request.getAvatar() );
        }
        if ( request.getStatus() != null ) {
            user.setStatus( request.getStatus() );
        }
    }

    @Override
    public void updateProfile(UpdateProfileRequest request, User user) {
        if ( request == null ) {
            return;
        }

        if ( request.getPhone() != null ) {
            user.setPhone( request.getPhone() );
        }
        if ( request.getAvatar() != null ) {
            user.setAvatar( request.getAvatar() );
        }
    }
}
