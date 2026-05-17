package com.example.springShop.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserFormDto {

    private Integer id;
    private String fullName;
    private String email;
    private String gender;
    private LocalDate birthday;
    private String avatar;

    private String role;
    private String password;
    private String confirmPassword;
}
