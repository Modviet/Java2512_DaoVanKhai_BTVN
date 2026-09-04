package com.folder.user_service.service.impl;

import com.folder.user_service.dto.request.CreateUserRequest;
import com.folder.user_service.dto.request.UpdateUserRequest;
import com.folder.user_service.exception.DuplicationException;
import com.folder.user_service.exception.ResourceNotFoundException;
import com.folder.user_service.security.KeycloakProperties;
import com.folder.user_service.service.KeycloakService;
import com.folder.user_service.util.NameUtils;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class KeycloakServiceImpl implements KeycloakService {

    private final Keycloak keycloak;

    private final KeycloakProperties keycloakProperties;

    private RealmResource realm(){
        return keycloak.realm(keycloakProperties.getRealm());
    }

    private UsersResource users(){
        return realm().users();
    }

    /**
     * ====================
     * CRUD
     * ===================
     */

    @Override
    public String createUser(CreateUserRequest request) {

        UserRepresentation user = new UserRepresentation();

        String[] name = NameUtils.splitFullName(request.getFullName());

        user.setUsername(request.getUsername());

        user.setEmail(request.getEmail());

        user.setFirstName(name[0]);

        user.setLastName(name[1]);

        user.setEnabled(true);

        user.setEmailVerified(true);

        Response response = users().create(user);

        try {

            if(response.getStatus() == 409){
                throw new DuplicationException("Username hoac Email da dang ky roi.");
            }

            if(response.getStatus() != 201){
                String error = response.readEntity(String.class);
                throw new RuntimeException("Khong the tao User moi tren Keycloak: "+ error);
            }

            String keycloakId = CreatedResponseUtil.getCreatedId(response);

            resetPassword(keycloakId, request.getPassword());

            log.info("Tao thanh cong Keycloak cua User : ", keycloakId);

            return keycloakId;
        } finally {
            response.close();
        }
    }

    @Override
    public void updateUser(String keycloakId, UpdateUserRequest request) {

        UserResource userResource = users().get(keycloakId);

        UserRepresentation user = userResource.toRepresentation();

        String[] name = NameUtils.splitFullName(request.getFullName());

        user.setFirstName(name[0]);
        user.setLastName(name[1]);
        user.setEmail(request.getEmail());

        switch (request.getStatus()) {
            case ACTIVE ->  user.setEnabled(true);
            case INACTIVE , LOCKED -> user.setEnabled(false);
        }

        userResource.update(user);

        log.info("Cap nhat Keycloak User thanh cong : ", keycloakId);

    }

    @Override
    public void deleteUser(String keycloakId) {

           users().get(keycloakId)
                   .remove();

           log.info("Xoa Keycloak User thanh cong : {}", keycloakId);
    }

    @Override
    public void enableUser(String keycloakId) {

          UserResource userResource = users().get(keycloakId);

          UserRepresentation user = userResource.toRepresentation();

          user.setEnabled(true);

          userResource.update(user);

          log.info("Mo khoa thanh cong User : {}", keycloakId);
    }

    @Override
    public void disableUser(String keycloakId) {

         UserResource userResource = users().get(keycloakId);

         UserRepresentation user = userResource.toRepresentation();

         user.setEnabled(false);

         userResource.update(user);

         log.info("Chan tai khoan cua User : {}", keycloakId);
    }

    @Override
    public void resetPassword(String keycloakId, String newPassword) {

        CredentialRepresentation credentail = new CredentialRepresentation();

        credentail.setType(CredentialRepresentation.PASSWORD);
        credentail.setValue(newPassword);
        credentail.setTemporary(false);

        users().get(keycloakId)
                .resetPassword(credentail);

        log.info("Thay doi mat khau thanh cong : {}", keycloakId);

    }

    @Override
    public String getKeycloakIdByUsername(String username) {

        List<UserRepresentation> result = users().searchByUsername(username, true);

        if(result.isEmpty()) {
            throw new ResourceNotFoundException("Khong tim thay User tren Keycloak.");
        }

        return result.getFirst().getId();
    }
}
