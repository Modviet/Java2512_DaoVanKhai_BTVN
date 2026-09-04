package com.folder.user_service.service.impl;

import com.folder.user_service.dto.request.CreateUserRequest;
import com.folder.user_service.dto.request.UpdateProfileRequest;
import com.folder.user_service.dto.request.UpdateUserRequest;
import com.folder.user_service.dto.response.UserResponse;
import com.folder.user_service.dto.response.UserSummaryResponse;
import com.folder.user_service.entity.User;
import com.folder.user_service.enums.UserStatus;
import com.folder.user_service.exception.DuplicationException;
import com.folder.user_service.exception.ResourceNotFoundException;
import com.folder.user_service.mapper.UserMapper;
import com.folder.user_service.repository.UserRepository;
import com.folder.user_service.service.KeycloakService;
import com.folder.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

       private final UserRepository userRepository;

       private final UserMapper userMapper;

       private final KeycloakService keycloakService;


    @Override
    @Transactional
    public UserResponse createUser(CreateUserRequest request) {

        if(userRepository.existsByUsername(request.getUsername())) {
            throw new DuplicationException("Username da dang ky");
        }

        if(userRepository.existsByEmail(request.getEmail())){
            throw new DuplicationException("Email da dang ky.");
        }

        String keycloakId = null;

        try {

            // 1. Tạo User trên Keycloak
            keycloakId = keycloakService.createUser(request);

            // 2. Mapping
            User user = userMapper.toEntity(request);

            user.setKeycloakId(keycloakId);
            user.setStatus(UserStatus.ACTIVE);

            // 3. Lưu PostgreSQL
            User savedUser = userRepository.save(user);

            log.info("Create User Success : {}", savedUser.getUsername());

            return userMapper.toResponse(savedUser);

        } catch (Exception ex) {

            // Nếu đã tạo trên Keycloak thì xóa để rollback
            if (keycloakId != null) {
                try {
                    keycloakService.deleteUser(keycloakId);
                    log.warn("Rollback Keycloak User Success : {}", keycloakId);
                } catch (Exception rollbackEx) {
                    log.error("Rollback Keycloak Failed : {}", rollbackEx.getMessage(), rollbackEx);
                }
            }

            throw ex;
        }
    }

    @Override
    public UserResponse updateUser(UUID id, UpdateUserRequest request) {

           User user = userRepository.findById(id)
                   .orElseThrow(()->
                           new ResourceNotFoundException("Khong tim thay User : "));

           keycloakService.updateUser(user.getKeycloakId(),
                   request);

           userMapper.updateUser(request, user);

           User updatedUser = userRepository.save(user);

           return userMapper.toResponse(updatedUser);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getUserById(UUID id) {

           User user = userRepository.findById(id)
                   .orElseThrow(()->
                           new ResourceNotFoundException("Khong tim thay User."));
           return userMapper.toResponse(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserSummaryResponse> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(userMapper::toSummaryResponse)
                .toList();
    }

    @Override
    public void deleteUser(UUID id) {

          User user = userRepository.findById(id)
                  .orElseThrow(()->
                          new ResourceNotFoundException("Khong tim thay User."));

          keycloakService.deleteUser(user.getKeycloakId());

          userRepository.delete(user);

          log.info("Da xoa User thanh cong : {}", user.getUsername());
    }

    @Override
    public void updateProfile(UUID id, UpdateProfileRequest request) {

          User user = userRepository.findById(id)
                  .orElseThrow(()->
                          new ResourceNotFoundException("Khong tim thay User."));

          userMapper.updateProfile(request, user);

          userRepository.save(user);

          log.info("Cap nhat thanh cong thong tin User : {}", user.getUsername());
    }
}
