package com.folder.productservice.entity;

import com.folder.productservice.util.UuidV7Util;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

       @Id
       @Column(nullable = false, updatable = false)
       private UUID id;

       @CreatedDate
       @Column(name = "created_at", nullable = false, updatable = false)
       private LocalDateTime createdAt;

       @LastModifiedDate
       @Column(name = "updated_at")
       private LocalDateTime updatedAt;

       @PrePersist
       protected void prePersist() {

           if (id == null) {
               id = UuidV7Util.generate();
           }

           if(createdAt == null) {
               createdAt = LocalDateTime.now();
           }

           updatedAt = LocalDateTime.now();
       }

       @PreUpdate
       protected void preUpdate() {
           updatedAt = LocalDateTime.now();
       }

}
