package com.folder.user_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProfileRequest {

      @NotBlank
      @Size(max = 150)
      private String fullName;

      @NotBlank
      @Size(max = 20)
      private String phone;

      @Size(max = 500)
      private String avatar;
}
