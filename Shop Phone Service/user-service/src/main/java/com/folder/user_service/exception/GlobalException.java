package com.folder.user_service.exception;

import com.folder.user_service.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {

       @ExceptionHandler(DuplicationException.class)
       public ResponseEntity<ApiResponse<Void>> handleDuplicateException(
               DuplicationException ex) {

           return ResponseEntity.status(HttpStatus.CONFLICT)
                   .body(ApiResponse.error(ex.getMessage()));
       }

       @ExceptionHandler(ResourceNotFoundException.class)
       public ResponseEntity<ApiResponse<Void>> handleResourceNotFoundException(
               ResourceNotFoundException ex) {

           return ResponseEntity.status(HttpStatus.NOT_FOUND)
                   .body(ApiResponse.error(ex.getMessage()));
       }

       @ExceptionHandler(MethodArgumentNotValidException.class)
       public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationException(
               MethodArgumentNotValidException exception) {

           Map<String, String> errors = new HashMap<>();

           for(FieldError error : exception.getBindingResult().getFieldErrors()) {
               errors.put(error.getField(), error.getDefaultMessage());
           }

           ApiResponse<Map<String, String>> response = ApiResponse.<Map<String, String>>builder()
                   .success(false)
                   .message("Validation failed")
                   .data(errors)
                   .timestamp(LocalDateTime.now())
                   .build();

           return ResponseEntity.badRequest().body(response);
       }

       @ExceptionHandler(Exception.class)
       public ResponseEntity<ApiResponse<Void>> handleException(
               Exception ex) {

           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(ApiResponse.error("Internal Server Error"));
       }
}
