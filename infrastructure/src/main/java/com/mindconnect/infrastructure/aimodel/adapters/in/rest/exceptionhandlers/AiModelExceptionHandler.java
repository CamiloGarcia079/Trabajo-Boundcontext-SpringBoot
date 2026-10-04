package com.mindconnect.infrastructure.aimodel.adapters.in.rest.exceptionhandlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindconnect.application.aimodel.exception.AiModelNotFoundApplicationException;
import com.mindconnect.infrastructure.aimodel.adapters.in.rest.controllers.AiModelController;
import com.mindconnect.infrastructure.common.dtos.ErrorResponse;

/**
 * Convierte el "no encontrado" de ai_models en una respuesta 404.
 * Solo aplica a AiModelController y se consulta antes que el manejador global.
 */
@RestControllerAdvice(assignableTypes = AiModelController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AiModelExceptionHandler {

    @ExceptionHandler(AiModelNotFoundApplicationException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(AiModelNotFoundApplicationException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(404, "Not Found", ex.getMessage()));
    }
}
