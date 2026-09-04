package com.folder.order_service.dto.response;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartResponse {

       private UUID id;

       private UUID userId;

       private List<CartItemResponse> items;
}
