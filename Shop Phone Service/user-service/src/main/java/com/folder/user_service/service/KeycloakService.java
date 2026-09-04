package com.folder.user_service.service;

import com.folder.user_service.dto.request.CreateUserRequest;
import com.folder.user_service.dto.request.UpdateUserRequest;

public interface KeycloakService {

       String createUser(CreateUserRequest request);

       void updateUser(String keycloakId, UpdateUserRequest request);

       void deleteUser(String keycloakId);

       void enableUser(String keycloakId);

       void disableUser(String keycloakId);

       void resetPassword(String keycloakId, String newPassword);

       String getKeycloakIdByUsername(String username);
}
