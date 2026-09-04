package com.folder.order_service.dto.request.cart;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddCartItemRequest {

       @NotNull(message = "Variant ID must not be null")
       private UUID varaintID;

       @NotNull(message = "Quantity must not be null")
       @Min(value = 1, message = "Quantity must be at least 1")
       private Integer quantity;
}
