package com.mindconnect.infrastructure.patient.adapters.in.rest.exceptionhandlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindconnect.application.patient.exception.PatientNotFoundApplicationException;
import com.mindconnect.infrastructure.patient.adapters.in.rest.controllers.PatientController;
import com.mindconnect.infrastructure.common.dtos.ErrorResponse;

/**
 * Convierte el "no encontrado" de patients en una respuesta 404.
 * Solo aplica a PatientController y se consulta antes que el manejador global.
 */
@RestControllerAdvice(assignableTypes = PatientController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class PatientExceptionHandler {

    @ExceptionHandler(PatientNotFoundApplicationException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(PatientNotFoundApplicationException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(404, "Not Found", ex.getMessage()));
    }
}
