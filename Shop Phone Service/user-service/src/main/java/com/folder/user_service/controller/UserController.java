package com.folder.user_service.controller;

import com.folder.user_service.dto.request.UpdateProfileRequest;
import com.folder.user_service.dto.request.UpdateUserRequest;
import com.folder.user_service.dto.response.UserResponse;
import com.folder.user_service.dto.response.UserSummaryResponse;
import com.folder.user_service.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Validated
public class UserController {

      private final UserService userService;

      @GetMapping
      public ResponseEntity<List<UserSummaryResponse>> getAllUsers() {

          return ResponseEntity.ok(userService.getAllUsers());
      }

      @GetMapping("/{id}")
      public ResponseEntity<UserResponse> getUserById(@PathVariable UUID id) {

          return ResponseEntity.ok(userService.getUserById(id));
      }

      @PutMapping("/{id}")
      public ResponseEntity<UserResponse> updateUser(@PathVariable UUID id,
                                                     @Valid @RequestBody UpdateUserRequest request) {

          return ResponseEntity.ok(userService.updateUser(id, request));
      }

      @DeleteMapping("/{id}")
      public ResponseEntity<Void> deleteUser(@PathVariable UUID id){

          userService.deleteUser(id);

          return ResponseEntity.noContent().build();
      }

    @PutMapping("/{id}/profile")
    public ResponseEntity<Void> updateProfile(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateProfileRequest request) {

        userService.updateProfile(id, request);

        return ResponseEntity.noContent().build();
    }
}
