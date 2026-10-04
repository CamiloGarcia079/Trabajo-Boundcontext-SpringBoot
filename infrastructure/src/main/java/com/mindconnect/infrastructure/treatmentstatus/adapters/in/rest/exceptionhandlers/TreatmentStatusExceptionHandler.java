package com.mindconnect.infrastructure.treatmentstatus.adapters.in.rest.exceptionhandlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindconnect.application.treatmentstatus.exception.TreatmentStatusNotFoundApplicationException;
import com.mindconnect.infrastructure.treatmentstatus.adapters.in.rest.controllers.TreatmentStatusController;
import com.mindconnect.infrastructure.common.dtos.ErrorResponse;

/**
 * Convierte el "no encontrado" de treatment_statuses en una respuesta 404.
 * Solo aplica a TreatmentStatusController y se consulta antes que el manejador global.
 */
@RestControllerAdvice(assignableTypes = TreatmentStatusController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TreatmentStatusExceptionHandler {

    @ExceptionHandler(TreatmentStatusNotFoundApplicationException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(TreatmentStatusNotFoundApplicationException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(404, "Not Found", ex.getMessage()));
    }
}
