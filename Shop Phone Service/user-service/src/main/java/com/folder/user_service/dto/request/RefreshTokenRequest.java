package com.folder.user_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefreshTokenRequest {

       @NotBlank(message = "Refresh Token khong duoc de trong.")
       private String refreshToken;
}
