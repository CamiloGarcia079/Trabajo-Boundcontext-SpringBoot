package com.mindconnect.infrastructure.common.dtos;

import java.time.LocalDateTime;

/**
 * Cuerpo JSON que devuelve la API cuando algo sale mal.
 */
public record ErrorResponse(int status, String error, String message, LocalDateTime timestamp) {

    public static ErrorResponse of(int status, String error, String message) {
        return new ErrorResponse(status, error, message, LocalDateTime.now());
    }
}
