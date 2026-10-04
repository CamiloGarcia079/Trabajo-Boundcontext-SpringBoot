package com.mindconnect.infrastructure.chatescalation.adapters.in.rest.exceptionhandlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindconnect.application.chatescalation.exception.ChatEscalationNotFoundApplicationException;
import com.mindconnect.infrastructure.chatescalation.adapters.in.rest.controllers.ChatEscalationController;
import com.mindconnect.infrastructure.common.dtos.ErrorResponse;

/**
 * Convierte el "no encontrado" de chat_escalations en una respuesta 404.
 * Solo aplica a ChatEscalationController y se consulta antes que el manejador global.
 */
@RestControllerAdvice(assignableTypes = ChatEscalationController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ChatEscalationExceptionHandler {

    @ExceptionHandler(ChatEscalationNotFoundApplicationException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ChatEscalationNotFoundApplicationException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(404, "Not Found", ex.getMessage()));
    }
}
