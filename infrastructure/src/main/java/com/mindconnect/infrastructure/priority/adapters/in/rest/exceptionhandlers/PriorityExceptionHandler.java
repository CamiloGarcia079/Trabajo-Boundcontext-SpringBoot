package com.mindconnect.infrastructure.priority.adapters.in.rest.exceptionhandlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindconnect.application.priority.exception.PriorityNotFoundApplicationException;
import com.mindconnect.infrastructure.priority.adapters.in.rest.controllers.PriorityController;
import com.mindconnect.infrastructure.common.dtos.ErrorResponse;

/**
 * Convierte el "no encontrado" de priorities en una respuesta 404.
 * Solo aplica a PriorityController y se consulta antes que el manejador global.
 */
@RestControllerAdvice(assignableTypes = PriorityController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class PriorityExceptionHandler {

    @ExceptionHandler(PriorityNotFoundApplicationException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(PriorityNotFoundApplicationException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(404, "Not Found", ex.getMessage()));
    }
}
