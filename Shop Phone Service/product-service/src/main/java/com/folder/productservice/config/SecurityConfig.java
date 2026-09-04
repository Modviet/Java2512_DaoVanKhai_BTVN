package com.folder.productservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

       @Bean
       public SecurityFilterChain securityFilterChain(
               HttpSecurity http
       ) throws Exception{

           http
                   .csrf(csrf-> csrf.disable())
                   .authorizeHttpRequests(auth -> auth.requestMatchers(
                           HttpMethod.GET,
                           "/api/v1/products/**",
                           "/api/v1/categories/**",
                           "/api/v1/brands/**"
                   ).permitAll()
                           .requestMatchers(HttpMethod.POST,
                                   "/api/v1/reviews")
                           .authenticated()
                           .requestMatchers("/api/v1/**")
                           .authenticated().anyRequest().permitAll());

           return http.build();
       }
}
