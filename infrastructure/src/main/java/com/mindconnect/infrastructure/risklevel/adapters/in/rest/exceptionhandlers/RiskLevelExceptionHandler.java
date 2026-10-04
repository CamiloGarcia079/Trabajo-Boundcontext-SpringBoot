package com.mindconnect.infrastructure.risklevel.adapters.in.rest.exceptionhandlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindconnect.application.risklevel.exception.RiskLevelNotFoundApplicationException;
import com.mindconnect.infrastructure.risklevel.adapters.in.rest.controllers.RiskLevelController;
import com.mindconnect.infrastructure.common.dtos.ErrorResponse;

/**
 * Convierte el "no encontrado" de risk_levels en una respuesta 404.
 * Solo aplica a RiskLevelController y se consulta antes que el manejador global.
 */
@RestControllerAdvice(assignableTypes = RiskLevelController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RiskLevelExceptionHandler {

    @ExceptionHandler(RiskLevelNotFoundApplicationException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(RiskLevelNotFoundApplicationException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(404, "Not Found", ex.getMessage()));
    }
}
