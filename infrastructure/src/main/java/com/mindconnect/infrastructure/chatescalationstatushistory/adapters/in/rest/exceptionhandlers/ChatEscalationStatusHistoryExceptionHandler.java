package com.mindconnect.infrastructure.chatescalationstatushistory.adapters.in.rest.exceptionhandlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindconnect.application.chatescalationstatushistory.exception.ChatEscalationStatusHistoryNotFoundApplicationException;
import com.mindconnect.infrastructure.chatescalationstatushistory.adapters.in.rest.controllers.ChatEscalationStatusHistoryController;
import com.mindconnect.infrastructure.common.dtos.ErrorResponse;

/**
 * Convierte el "no encontrado" de chat_escalation_status_history en una respuesta 404.
 * Solo aplica a ChatEscalationStatusHistoryController y se consulta antes que el manejador global.
 */
@RestControllerAdvice(assignableTypes = ChatEscalationStatusHistoryController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ChatEscalationStatusHistoryExceptionHandler {

    @ExceptionHandler(ChatEscalationStatusHistoryNotFoundApplicationException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ChatEscalationStatusHistoryNotFoundApplicationException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(404, "Not Found", ex.getMessage()));
    }
}
