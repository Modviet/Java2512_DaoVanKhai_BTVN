package com.folder.user_service.entity;

import com.folder.user_service.util.UUIDUtils;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

       @Id
       @Column(nullable = false, updatable = false)
       private UUID id;

       @Column(name = "created_at", nullable = false, updatable = false)
       private LocalDateTime createdAt;

       @Column(name = "updated_at", nullable = false)
       private LocalDateTime updatedAt;

       @PrePersist
       protected void prePersist() {

           if(this.id == null){
               this.id = UUIDUtils.generateUUIDv7();
           }

           LocalDateTime now = LocalDateTime.now();

           this.createdAt = now;
           this.updatedAt = now;
       }

       @PreUpdate
       protected void preUpdate() {
           this.updatedAt = LocalDateTime.now();
       }

}
