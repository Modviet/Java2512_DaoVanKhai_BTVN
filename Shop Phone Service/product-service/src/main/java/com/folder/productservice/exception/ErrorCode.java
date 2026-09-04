package com.folder.productservice.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND),

    ALREADY_EXISTS(HttpStatus.BAD_REQUEST),

    INVALID_REQUEST(HttpStatus.BAD_REQUEST),

    UNAUTHORIZED(HttpStatus.UNAUTHORIZED),

    FORBIDDEN(HttpStatus.FORBIDDEN),

    INSUFFICIENT_STOCK(HttpStatus.BAD_REQUEST),

    UPLOAD_FAILED(HttpStatus.INTERNAL_SERVER_ERROR),

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR);

    private final HttpStatus status;



}
