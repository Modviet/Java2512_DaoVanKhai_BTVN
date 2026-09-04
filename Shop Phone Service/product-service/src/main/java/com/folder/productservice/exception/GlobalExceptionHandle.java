package com.folder.productservice.exception;

import com.folder.productservice.dto.response.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandle {

      @ExceptionHandler(AppException.class)
      public ResponseEntity<ErrorResponse> handleAppException(AppException ex) {

            ErrorCode errorCode = ex.getErrorCode();

            return ResponseEntity.status(errorCode.getStatus())
                    .body(ErrorResponse.builder()
                            .status(errorCode.getStatus().value())
                            .error(errorCode.getStatus().getReasonPhrase())
                            .message(ex.getMessage())
                            .timestamp(LocalDateTime.now())
                            .build());
      }

      @ExceptionHandler(Exception.class)
      public ResponseEntity<ErrorResponse> handleException(Exception ex){

            return ResponseEntity.status(ErrorCode.INTERNAL_SERVER_ERROR.getStatus())
                    .body(ErrorResponse.builder()
                            .status(ErrorCode.INTERNAL_SERVER_ERROR.getStatus().value())
                            .error(ErrorCode.INTERNAL_SERVER_ERROR.getStatus().getReasonPhrase())
                            .message("Internal server error")
                            .timestamp(LocalDateTime.now())
                            .build());
      }
}
