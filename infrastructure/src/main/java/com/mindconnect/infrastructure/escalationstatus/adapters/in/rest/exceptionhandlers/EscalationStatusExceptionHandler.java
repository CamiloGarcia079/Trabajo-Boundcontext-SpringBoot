package com.mindconnect.infrastructure.escalationstatus.adapters.in.rest.exceptionhandlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindconnect.application.escalationstatus.exception.EscalationStatusNotFoundApplicationException;
import com.mindconnect.infrastructure.escalationstatus.adapters.in.rest.controllers.EscalationStatusController;
import com.mindconnect.infrastructure.common.dtos.ErrorResponse;

/**
 * Convierte el "no encontrado" de escalations_statuses en una respuesta 404.
 * Solo aplica a EscalationStatusController y se consulta antes que el manejador global.
 */
@RestControllerAdvice(assignableTypes = EscalationStatusController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class EscalationStatusExceptionHandler {

    @ExceptionHandler(EscalationStatusNotFoundApplicationException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(EscalationStatusNotFoundApplicationException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(404, "Not Found", ex.getMessage()));
    }
}
