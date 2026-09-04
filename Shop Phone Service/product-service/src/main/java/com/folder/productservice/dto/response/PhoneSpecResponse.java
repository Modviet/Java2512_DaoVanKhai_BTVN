package com.folder.productservice.dto.response;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhoneSpecResponse {

       private UUID id;

       private String screenSize;

       private String resolution;

       private String refreshRate;

       private String chip;

       private String operatingSystem;

       private String batteryCapacity;

       private String frontCamera;

       private String rearCamera;
}
