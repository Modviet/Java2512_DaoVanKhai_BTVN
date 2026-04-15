package com.folder.model;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private Integer id;
    private String fullName;
    private String email;
    private String passwordHash;
    private Date birthday;
    private String gender;
    private String role;
    private String avatar;
    private Date createdAt;
    private Date updatedAt;
}
