package com.folder.user_service.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {

       @JsonProperty("access_token")
       private String accessToken;

       @JsonProperty("expires_in")
       private Long expiresIn;

       @JsonProperty("refresh_expires_in")
       private Long refreshExpiresIn;

       @JsonProperty("refresh_token")
       private String refreshToken;

       @JsonProperty("token_type")
       private String tokenType;

       @JsonProperty("scope")
       private String scope;
}
