package com.folder.user_service.entity;

import com.folder.user_service.enums.UserStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email"),
                @UniqueConstraint(columnNames = "phone"),
                @UniqueConstraint(columnNames = "keycloak_id")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity{

       @Column(name = "keycloak_id", nullable = false, length = 100)
       private String keycloakId;

       @Column(nullable = false, length = 50)
       private String username;

       @Column(nullable = false, length = 100)
       private String email;

       @Column(nullable = false, length = 20)
       private String phone;

       @Column(length = 500)
       private String avatar;

       @Enumerated(EnumType.STRING)
       @Column(nullable = false)
       private UserStatus status;


}
