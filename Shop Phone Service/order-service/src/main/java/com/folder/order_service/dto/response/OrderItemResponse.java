package com.folder.order_service.dto.response;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemResponse {

      private UUID id;

      private String variant;

      private String productName;

      private String ram;

      private String storage;

      private String color;

      private Float price;

      private Integer quantity;
}
