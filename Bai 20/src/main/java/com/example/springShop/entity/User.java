package com.example.springShop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity{

    @Column(name = "full_name",length = 100)
    private String fullName;

    @Column(nullable = false,unique = true,length = 255)
    private String email;

    @Column(name = "password_hash",nullable = false,length = 255)
    private String passwordHash;

    private LocalDate birthday;

    @Column(columnDefinition = "ENUM('Male','Female','Decline to state')")
    private String gender;

    @Column(name = "remember_token",length = 255)
    private String rememberToken;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST,CascadeType.MERGE}
    )
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @Column(length = 500)
    private String avatar;

}
