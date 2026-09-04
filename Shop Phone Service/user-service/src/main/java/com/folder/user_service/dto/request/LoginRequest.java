package com.folder.user_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequest {

      @NotBlank(message = "Username khong duoc de trong.")
      private String username;

      @NotBlank(message = "Password khong duoc de trong.")
      private String passwrord;
}
