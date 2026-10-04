package com.mindconnect.infrastructure.encountermodality.adapters.in.rest.exceptionhandlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindconnect.application.encountermodality.exception.EncounterModalityNotFoundApplicationException;
import com.mindconnect.infrastructure.encountermodality.adapters.in.rest.controllers.EncounterModalityController;
import com.mindconnect.infrastructure.common.dtos.ErrorResponse;

/**
 * Convierte el "no encontrado" de encounter_modalities en una respuesta 404.
 * Solo aplica a EncounterModalityController y se consulta antes que el manejador global.
 */
@RestControllerAdvice(assignableTypes = EncounterModalityController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class EncounterModalityExceptionHandler {

    @ExceptionHandler(EncounterModalityNotFoundApplicationException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(EncounterModalityNotFoundApplicationException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(404, "Not Found", ex.getMessage()));
    }
}
