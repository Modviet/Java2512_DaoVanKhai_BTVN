package com.folder.user_service.dto.request;

import com.folder.user_service.enums.UserStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {

        @NotBlank
        @Size(max = 150)
        private String fullName;

        @NotBlank
        @Email
        @Size(max = 100)
        private String email;

        @NotBlank
        @Size(max = 20)
        private String phone;

        @Size(max = 500)
        private String avatar;

        @NotNull
        private UserStatus status;
}
