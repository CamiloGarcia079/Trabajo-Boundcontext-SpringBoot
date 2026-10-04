package com.mindconnect.infrastructure.stateregion.adapters.in.rest.exceptionhandlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindconnect.application.stateregion.exception.StateRegionNotFoundApplicationException;
import com.mindconnect.infrastructure.stateregion.adapters.in.rest.controllers.StateRegionController;
import com.mindconnect.infrastructure.common.dtos.ErrorResponse;

/**
 * Convierte el "no encontrado" de state_regions en una respuesta 404.
 * Solo aplica a StateRegionController y se consulta antes que el manejador global.
 */
@RestControllerAdvice(assignableTypes = StateRegionController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class StateRegionExceptionHandler {

    @ExceptionHandler(StateRegionNotFoundApplicationException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(StateRegionNotFoundApplicationException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(404, "Not Found", ex.getMessage()));
    }
}
