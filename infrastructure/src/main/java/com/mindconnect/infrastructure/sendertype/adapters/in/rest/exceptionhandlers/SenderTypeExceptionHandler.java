package com.mindconnect.infrastructure.sendertype.adapters.in.rest.exceptionhandlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindconnect.application.sendertype.exception.SenderTypeNotFoundApplicationException;
import com.mindconnect.infrastructure.sendertype.adapters.in.rest.controllers.SenderTypeController;
import com.mindconnect.infrastructure.common.dtos.ErrorResponse;

/**
 * Convierte el "no encontrado" de sender_types en una respuesta 404.
 * Solo aplica a SenderTypeController y se consulta antes que el manejador global.
 */
@RestControllerAdvice(assignableTypes = SenderTypeController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SenderTypeExceptionHandler {

    @ExceptionHandler(SenderTypeNotFoundApplicationException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(SenderTypeNotFoundApplicationException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(404, "Not Found", ex.getMessage()));
    }
}
