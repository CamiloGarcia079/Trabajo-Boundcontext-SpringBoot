package com.mindconnect.infrastructure.patientcontact.adapters.in.rest.exceptionhandlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindconnect.application.patientcontact.exception.PatientContactNotFoundApplicationException;
import com.mindconnect.infrastructure.patientcontact.adapters.in.rest.controllers.PatientContactController;
import com.mindconnect.infrastructure.common.dtos.ErrorResponse;

/**
 * Convierte el "no encontrado" de patient_contacts en una respuesta 404.
 * Solo aplica a PatientContactController y se consulta antes que el manejador global.
 */
@RestControllerAdvice(assignableTypes = PatientContactController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class PatientContactExceptionHandler {

    @ExceptionHandler(PatientContactNotFoundApplicationException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(PatientContactNotFoundApplicationException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(404, "Not Found", ex.getMessage()));
    }
}
