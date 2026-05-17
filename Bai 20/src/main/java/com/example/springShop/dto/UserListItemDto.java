package com.example.springShop.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class UserListItemDto {

    private Integer id;
    private String fullName;
    private String email;
    private String gender;
    private LocalDate birthday;
    private String avatar;

    private String role;
    private LocalDateTime createdAt;

}
