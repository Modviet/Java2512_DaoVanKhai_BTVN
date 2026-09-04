package com.folder.productservice.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private boolean success;

    private String message;

    private T result;

    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();

    /**
     * Success - mặc định
     */
    public static <T> ApiResponse<T> success(T result) {
        return ApiResponse.<T>builder()
                .success(true)
                .message("Success")

                .build();
    }

    /**
     * Success - custom message
     */
    public static <T> ApiResponse<T> success(String message, T result) {
        return ApiResponse.<T>builder()
                .success(true)
                .message(message)
                .result(result)
                .build();
    }

    /**
     * Success - không có result
     */
    public static <T> ApiResponse<T> success(String message) {
        return ApiResponse.<T>builder()
                .success(true)
                .message(message)
                .build();
    }

    /**
     * Error - không có result
     */
    public static <T> ApiResponse<T> error(String message) {
        return ApiResponse.<T>builder()
                .success(false)
                .message(message)
                .build();
    }

    /**
     * Error - có result
     */
    public static <T> ApiResponse<T> error(String message, T result) {
        return ApiResponse.<T>builder()
                .success(false)
                .message(message)
                .result(result)
                .build();
    }

}
