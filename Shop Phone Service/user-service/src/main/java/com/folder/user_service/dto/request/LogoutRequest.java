package com.folder.user_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LogoutRequest {

      @NotBlank(message = "Refresh Token khong duoc de trong.")
      private String refreshToken;

}
