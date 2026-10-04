package com.mindconnect.infrastructure.encounter.adapters.in.rest.exceptionhandlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindconnect.application.encounter.exception.EncounterNotFoundApplicationException;
import com.mindconnect.infrastructure.encounter.adapters.in.rest.controllers.EncounterController;
import com.mindconnect.infrastructure.common.dtos.ErrorResponse;

/**
 * Convierte el "no encontrado" de encounters en una respuesta 404.
 * Solo aplica a EncounterController y se consulta antes que el manejador global.
 */
@RestControllerAdvice(assignableTypes = EncounterController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class EncounterExceptionHandler {

    @ExceptionHandler(EncounterNotFoundApplicationException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(EncounterNotFoundApplicationException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(404, "Not Found", ex.getMessage()));
    }
}
