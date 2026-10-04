package com.mindconnect.infrastructure.chatairunmetric.adapters.in.rest.exceptionhandlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindconnect.application.chatairunmetric.exception.ChatAiRunMetricNotFoundApplicationException;
import com.mindconnect.infrastructure.chatairunmetric.adapters.in.rest.controllers.ChatAiRunMetricController;
import com.mindconnect.infrastructure.common.dtos.ErrorResponse;

/**
 * Convierte el "no encontrado" de chat_ai_run_metrics en una respuesta 404.
 * Solo aplica a ChatAiRunMetricController y se consulta antes que el manejador global.
 */
@RestControllerAdvice(assignableTypes = ChatAiRunMetricController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ChatAiRunMetricExceptionHandler {

    @ExceptionHandler(ChatAiRunMetricNotFoundApplicationException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ChatAiRunMetricNotFoundApplicationException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(404, "Not Found", ex.getMessage()));
    }
}
