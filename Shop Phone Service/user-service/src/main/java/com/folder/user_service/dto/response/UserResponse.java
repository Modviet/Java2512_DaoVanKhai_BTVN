package com.folder.user_service.dto.response;

import com.folder.user_service.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

       private UUID id;

       private String username;

       private String fullName;

       private String email;

       private String phone;

       private String avatar;

       private UserStatus status;

       private LocalDateTime createdAt;

       private LocalDateTime updatedAt;
}
