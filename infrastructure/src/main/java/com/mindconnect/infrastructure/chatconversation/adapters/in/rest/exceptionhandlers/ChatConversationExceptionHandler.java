package com.mindconnect.infrastructure.chatconversation.adapters.in.rest.exceptionhandlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindconnect.application.chatconversation.exception.ChatConversationNotFoundApplicationException;
import com.mindconnect.infrastructure.chatconversation.adapters.in.rest.controllers.ChatConversationController;
import com.mindconnect.infrastructure.common.dtos.ErrorResponse;

/**
 * Convierte el "no encontrado" de chat_conversations en una respuesta 404.
 * Solo aplica a ChatConversationController y se consulta antes que el manejador global.
 */
@RestControllerAdvice(assignableTypes = ChatConversationController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ChatConversationExceptionHandler {

    @ExceptionHandler(ChatConversationNotFoundApplicationException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ChatConversationNotFoundApplicationException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(404, "Not Found", ex.getMessage()));
    }
}
