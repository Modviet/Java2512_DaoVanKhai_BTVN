package com.folder.productservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatePhoneSpecRequest {

       @NotNull
       private UUID productId;

       @NotBlank
       private String screenSize;

       @NotBlank
       private String resolution;

       @NotBlank
       private String refreshRate;

       @NotBlank
       private String chip;

       @NotBlank
       private String operatingSystem;

       @NotBlank
       private String batteryCapacity;

       private String frontCamera;

       private String rearCamera;

}
