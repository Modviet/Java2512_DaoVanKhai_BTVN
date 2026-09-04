package com.folder.user_service.dto.response;

import com.folder.user_service.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSummaryResponse {

       private UUID id;

       private String username;

       private String fullName;

       private UserStatus status;
}
