package com.folder.order_service.exception;

import com.folder.order_service.exception.common.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {

       @ExceptionHandler(ResourceNotFoundException.class)
       public ResponseEntity<ApiResponse<Void>> handleNotFound(ResourceNotFoundException ex){

             return ResponseEntity.status(HttpStatus.NOT_FOUND)
                     .body(ApiResponse.error(ex.getMessage()));
       }

       @ExceptionHandler(BusinessException.class)
       public ResponseEntity<ApiResponse<Void>> handleBusiness(BusinessException ex){

              return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                      .body(ApiResponse.error(ex.getMessage()));
       }

       @ExceptionHandler(MethodArgumentNotValidException.class)
       public ResponseEntity<ApiResponse<Void>> handleValidation(MethodArgumentNotValidException ex){

              String message = ex.getBindingResult()
                      .getFieldErrors()
                      .stream()
                      .findFirst()
                      .map(error-> error.getField()+
                              ":" + error.getDefaultMessage()).orElse("Invalid request");

              return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                      .body(ApiResponse.error(message));
       }

       @ExceptionHandler(Exception.class)
       public ResponseEntity<ApiResponse<Void>> handleException(
               Exception ex
       ){

         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                 .body(ApiResponse.error("Internal server error"));
       }
}
