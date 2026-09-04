package com.folder.order_service.dto.response;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItemResponse {

       private UUID id;

       private UUID varaintId;

       private Integer quantity;
}
