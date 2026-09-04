package com.folder.user_service.dto.request;

import com.folder.user_service.enums.UserStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChangeStatusRequest {

       @NotNull
       private UserStatus status;
}
