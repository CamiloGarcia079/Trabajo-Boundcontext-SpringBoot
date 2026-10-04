package com.mindconnect.infrastructure.gender.adapters.in.rest.exceptionhandlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindconnect.application.gender.exception.GenderNotFoundApplicationException;
import com.mindconnect.infrastructure.gender.adapters.in.rest.controllers.GenderController;
import com.mindconnect.infrastructure.common.dtos.ErrorResponse;

/**
 * Convierte el "no encontrado" de genders en una respuesta 404.
 * Solo aplica a GenderController y se consulta antes que el manejador global.
 */
@RestControllerAdvice(assignableTypes = GenderController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GenderExceptionHandler {

    @ExceptionHandler(GenderNotFoundApplicationException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(GenderNotFoundApplicationException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(404, "Not Found", ex.getMessage()));
    }
}
