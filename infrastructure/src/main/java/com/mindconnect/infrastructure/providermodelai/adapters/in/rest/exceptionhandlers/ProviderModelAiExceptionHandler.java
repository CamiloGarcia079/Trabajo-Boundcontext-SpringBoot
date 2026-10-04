package com.mindconnect.infrastructure.providermodelai.adapters.in.rest.exceptionhandlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindconnect.application.providermodelai.exception.ProviderModelAiNotFoundApplicationException;
import com.mindconnect.infrastructure.providermodelai.adapters.in.rest.controllers.ProviderModelAiController;
import com.mindconnect.infrastructure.common.dtos.ErrorResponse;

/**
 * Convierte el "no encontrado" de provider_models_ai en una respuesta 404.
 * Solo aplica a ProviderModelAiController y se consulta antes que el manejador global.
 */
@RestControllerAdvice(assignableTypes = ProviderModelAiController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ProviderModelAiExceptionHandler {

    @ExceptionHandler(ProviderModelAiNotFoundApplicationException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ProviderModelAiNotFoundApplicationException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(404, "Not Found", ex.getMessage()));
    }
}
