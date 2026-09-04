package com.folder.user_service.service;

import com.folder.user_service.dto.request.CreateUserRequest;
import com.folder.user_service.dto.request.UpdateProfileRequest;
import com.folder.user_service.dto.request.UpdateUserRequest;
import com.folder.user_service.dto.response.UserResponse;
import com.folder.user_service.dto.response.UserSummaryResponse;

import java.util.List;
import java.util.UUID;

public interface UserService {

       UserResponse createUser(CreateUserRequest request);

       UserResponse updateUser(UUID id, UpdateUserRequest request);

       UserResponse getUserById(UUID id);

       List<UserSummaryResponse> getAllUsers();

       void deleteUser(UUID id);

       void updateProfile(UUID id, UpdateProfileRequest request);

}
