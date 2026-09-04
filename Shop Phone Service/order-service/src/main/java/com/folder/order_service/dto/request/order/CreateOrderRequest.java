package com.folder.order_service.dto.request.order;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest {

        @NotNull(message = "User ID must not be null")
        private UUID userId;

        @NotBlank(message = "Receiver name must not be empty")
        @Size(max = 100, message = "Receiver name must not exceed 100 char")
        private String receiverName;

        @NotBlank(message = "Receiver phone must not be empty")
        @Size(max = 20, message = "Receiver phone must not exceed 20 char")
        private String receiverPhone;

        @NotBlank(message = "Province must not be empty")
        @Size(max = 100, message = "Province must not be exceed 100 char")
        private String province;

        @NotBlank(message = "District must not be empty")
        @Size(max = 100, message = "District must not exceed 100 char")
        private String district;

        @NotBlank(message = "Ward must not be empty")
        @Size(max = 100, message = "Ward must not exceed 100 char")
        private String ward;

        @NotBlank(message = "Detail address must not be empty")
        @Size(max = 255, message = "Detail address must not exceed 255 char")
        private String detailAddress;
}
