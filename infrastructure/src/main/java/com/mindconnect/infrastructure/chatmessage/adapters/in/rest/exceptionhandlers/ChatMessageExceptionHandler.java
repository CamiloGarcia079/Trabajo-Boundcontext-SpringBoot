package com.mindconnect.infrastructure.chatmessage.adapters.in.rest.exceptionhandlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindconnect.application.chatmessage.exception.ChatMessageNotFoundApplicationException;
import com.mindconnect.infrastructure.chatmessage.adapters.in.rest.controllers.ChatMessageController;
import com.mindconnect.infrastructure.common.dtos.ErrorResponse;

/**
 * Convierte el "no encontrado" de chat_messages en una respuesta 404.
 * Solo aplica a ChatMessageController y se consulta antes que el manejador global.
 */
@RestControllerAdvice(assignableTypes = ChatMessageController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ChatMessageExceptionHandler {

    @ExceptionHandler(ChatMessageNotFoundApplicationException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ChatMessageNotFoundApplicationException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(404, "Not Found", ex.getMessage()));
    }
}
