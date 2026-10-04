package com.mindconnect.infrastructure.consenttype.adapters.in.rest.exceptionhandlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindconnect.application.consenttype.exception.ConsentTypeNotFoundApplicationException;
import com.mindconnect.infrastructure.consenttype.adapters.in.rest.controllers.ConsentTypeController;
import com.mindconnect.infrastructure.common.dtos.ErrorResponse;

/**
 * Convierte el "no encontrado" de consent_types en una respuesta 404.
 * Solo aplica a ConsentTypeController y se consulta antes que el manejador global.
 */
@RestControllerAdvice(assignableTypes = ConsentTypeController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ConsentTypeExceptionHandler {

    @ExceptionHandler(ConsentTypeNotFoundApplicationException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ConsentTypeNotFoundApplicationException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(404, "Not Found", ex.getMessage()));
    }
}
