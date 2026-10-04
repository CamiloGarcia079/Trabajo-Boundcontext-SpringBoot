package com.mindconnect.infrastructure.chatconversationaisetting.adapters.in.rest.exceptionhandlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindconnect.application.chatconversationaisetting.exception.ChatConversationAiSettingNotFoundApplicationException;
import com.mindconnect.infrastructure.chatconversationaisetting.adapters.in.rest.controllers.ChatConversationAiSettingController;
import com.mindconnect.infrastructure.common.dtos.ErrorResponse;

/**
 * Convierte el "no encontrado" de chat_conversation_ai_settings en una respuesta 404.
 * Solo aplica a ChatConversationAiSettingController y se consulta antes que el manejador global.
 */
@RestControllerAdvice(assignableTypes = ChatConversationAiSettingController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ChatConversationAiSettingExceptionHandler {

    @ExceptionHandler(ChatConversationAiSettingNotFoundApplicationException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ChatConversationAiSettingNotFoundApplicationException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(404, "Not Found", ex.getMessage()));
    }
}
