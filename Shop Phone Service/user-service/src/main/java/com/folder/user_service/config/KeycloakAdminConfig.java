package com.folder.user_service.config;

import com.folder.user_service.security.KeycloakProperties;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakAdminConfig {

       @Bean
       public Keycloak keycloakAdmin(KeycloakProperties properties) {

           return KeycloakBuilder.builder()
                   .serverUrl(properties.getServerUrl())
                   .realm(properties.getRealm())
                   .clientId(properties.getClientId())
                   .clientSecret(properties.getClientSecret())
                   .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                   .build();
       }
}
