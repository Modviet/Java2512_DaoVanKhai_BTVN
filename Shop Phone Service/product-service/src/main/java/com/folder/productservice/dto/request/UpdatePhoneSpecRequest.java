package com.folder.productservice.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdatePhoneSpecRequest {

      private String screenSize;

      private String resolution;

      private String refreshRate;

      private String chip;

      private String operatingSystem;

      private String batteryCapacity;

      private String frontCamera;

      private String rearCamera;
}
