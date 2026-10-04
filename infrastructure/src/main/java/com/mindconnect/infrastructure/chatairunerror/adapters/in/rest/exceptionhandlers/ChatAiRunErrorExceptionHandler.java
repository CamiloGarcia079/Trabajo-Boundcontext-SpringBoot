package com.mindconnect.infrastructure.chatairunerror.adapters.in.rest.exceptionhandlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindconnect.application.chatairunerror.exception.ChatAiRunErrorNotFoundApplicationException;
import com.mindconnect.infrastructure.chatairunerror.adapters.in.rest.controllers.ChatAiRunErrorController;
import com.mindconnect.infrastructure.common.dtos.ErrorResponse;

/**
 * Convierte el "no encontrado" de chat_ai_run_errors en una respuesta 404.
 * Solo aplica a ChatAiRunErrorController y se consulta antes que el manejador global.
 */
@RestControllerAdvice(assignableTypes = ChatAiRunErrorController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ChatAiRunErrorExceptionHandler {

    @ExceptionHandler(ChatAiRunErrorNotFoundApplicationException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ChatAiRunErrorNotFoundApplicationException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(404, "Not Found", ex.getMessage()));
    }
}
