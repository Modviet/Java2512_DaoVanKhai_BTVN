package com.folder.user_service.controller;

import com.folder.user_service.dto.request.CreateUserRequest;
import com.folder.user_service.dto.request.LoginRequest;
import com.folder.user_service.dto.request.LogoutRequest;
import com.folder.user_service.dto.request.RefreshTokenRequest;
import com.folder.user_service.dto.response.LoginResponse;
import com.folder.user_service.dto.response.UserResponse;
import com.folder.user_service.security.KeycloakProperties;
import com.folder.user_service.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Validated
public class AuthController {

      private final UserService userService;

      private final RestClient restClient;

      private final KeycloakProperties keycloakProperties;

      @PostMapping("/register")
      public ResponseEntity<UserResponse> register(
              @Valid @RequestBody CreateUserRequest request) {

          UserResponse response = userService.createUser(request);

          return ResponseEntity.status(HttpStatus.CREATED)
                  .body(response);
      }

      @PostMapping("/login")
      public ResponseEntity<LoginResponse> login(
              @Valid @RequestBody LoginRequest request) {

          MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();

          formData.add("grant_type","password");
          formData.add("client_id", keycloakProperties.getClientId());
          formData.add("client_sercet", keycloakProperties.getClientSecret());
          formData.add("username", request.getUsername());
          formData.add("password", request.getPasswrord());

          try {
              LoginResponse response = restClient.post()
                      .uri(keycloakProperties.getServerUrl()
                              + "/realms/"
                              + keycloakProperties.getRealm()
                              + "/protocol/openid-connect/token")
                      .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                      .body(formData)
                      .retrieve()
                      .body(LoginResponse.class);

              return ResponseEntity.ok(response);
          } catch (RestClientResponseException ex) {
              if (ex.getStatusCode() == HttpStatus.UNAUTHORIZED
                      || ex.getStatusCode() == HttpStatus.BAD_REQUEST) {

                  throw new BadCredentialsException("Ten dang nhap hoac mat khau khong dung.");
              }
              throw new RuntimeException("Khong the ket noi toi Keycloak.", ex);
          }

      }


      @PostMapping("/refresh")
      public ResponseEntity<LoginResponse> refreshToken(
              @Valid @RequestBody RefreshTokenRequest request
              ) {

          MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();

          formData.add("grand_type", "refresh_token");
          formData.add("client_id", keycloakProperties.getClientId());
          formData.add("client_secret", keycloakProperties.getClientSecret());
          formData.add("refresh_token", request.getRefreshToken());

          try {

              LoginResponse response = restClient.post()
                      .uri(keycloakProperties.getServerUrl()
                      + "/realms"
                      +keycloakProperties.getRealm()
                      + "/protocol/openid-connect/token")
                      .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                      .body(formData)
                      .retrieve()
                      .body(LoginResponse.class);

              return ResponseEntity.ok(response);
          } catch (RestClientResponseException ex) {

              if(ex.getStatusCode() == HttpStatus.BAD_REQUEST ||
                    ex.getStatusCode() == HttpStatus.UNAUTHORIZED) {

                  throw new BadCredentialsException(
                          "Refresh Token khong hop le hoac het han.");
              }

              throw new RuntimeException("Khong the ket noi toi Keycloak.");
          }
      }


      @PostMapping("/logout")
      public ResponseEntity<Void> logout(
              @Valid @RequestBody LogoutRequest request) {

          MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();

          formData.add("client_id", keycloakProperties.getClientId());
          formData.add("client_sercet", keycloakProperties.getClientSecret());
          formData.add("refresh_token", request.getRefreshToken());

          try {

              restClient.post()
                      .uri(keycloakProperties.getServerUrl()
                      + "/realms/"
                      + keycloakProperties.getRealm()
                      + "/protocol/openid-connect/logout")
                      .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                      .body(formData)
                      .retrieve()
                      .toBodilessEntity();

              return ResponseEntity.noContent().build();
          } catch (RestClientResponseException ex) {

              throw new RuntimeException("Dang xuat that bai.");
          }
      }



}
