package com.mindconnect.infrastructure.messagetype.adapters.in.rest.exceptionhandlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindconnect.application.messagetype.exception.MessageTypeNotFoundApplicationException;
import com.mindconnect.infrastructure.messagetype.adapters.in.rest.controllers.MessageTypeController;
import com.mindconnect.infrastructure.common.dtos.ErrorResponse;

/**
 * Convierte el "no encontrado" de message_types en una respuesta 404.
 * Solo aplica a MessageTypeController y se consulta antes que el manejador global.
 */
@RestControllerAdvice(assignableTypes = MessageTypeController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MessageTypeExceptionHandler {

    @ExceptionHandler(MessageTypeNotFoundApplicationException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(MessageTypeNotFoundApplicationException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(404, "Not Found", ex.getMessage()));
    }
}
