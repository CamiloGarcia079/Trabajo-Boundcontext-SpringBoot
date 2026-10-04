package com.mindconnect.infrastructure.diagnosticsystem.adapters.in.rest.exceptionhandlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindconnect.application.diagnosticsystem.exception.DiagnosticSystemNotFoundApplicationException;
import com.mindconnect.infrastructure.diagnosticsystem.adapters.in.rest.controllers.DiagnosticSystemController;
import com.mindconnect.infrastructure.common.dtos.ErrorResponse;

/**
 * Convierte el "no encontrado" de diagnostic_systems en una respuesta 404.
 * Solo aplica a DiagnosticSystemController y se consulta antes que el manejador global.
 */
@RestControllerAdvice(assignableTypes = DiagnosticSystemController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class DiagnosticSystemExceptionHandler {

    @ExceptionHandler(DiagnosticSystemNotFoundApplicationException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(DiagnosticSystemNotFoundApplicationException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(404, "Not Found", ex.getMessage()));
    }
}
