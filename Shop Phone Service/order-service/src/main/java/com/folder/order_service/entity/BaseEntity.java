package com.folder.order_service.entity;

import com.folder.order_service.util.UuidV7Generator;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {

      @Id
      @Column(nullable = false, updatable = false)
      private UUID id;

      @Column(name = "created_at", nullable = false, updatable = false)
      private LocalDateTime createdAt;

      @Column(name = "updated_at", nullable = false)
      private LocalDateTime updatedAt;

      @PrePersist
      protected void onCreate(){

          if(id== null){
              id = UuidV7Generator.generate();
          }

          LocalDateTime now = LocalDateTime.now();
          createdAt = now;
          updatedAt = now;
      }

      @PreUpdate
      protected void onUpdate(){
          updatedAt = LocalDateTime.now();
      }
}
