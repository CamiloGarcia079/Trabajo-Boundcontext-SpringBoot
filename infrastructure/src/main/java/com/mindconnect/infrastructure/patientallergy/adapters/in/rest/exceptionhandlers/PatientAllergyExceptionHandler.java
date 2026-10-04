package com.mindconnect.infrastructure.patientallergy.adapters.in.rest.exceptionhandlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindconnect.application.patientallergy.exception.PatientAllergyNotFoundApplicationException;
import com.mindconnect.infrastructure.patientallergy.adapters.in.rest.controllers.PatientAllergyController;
import com.mindconnect.infrastructure.common.dtos.ErrorResponse;

/**
 * Convierte el "no encontrado" de patient_allergies en una respuesta 404.
 * Solo aplica a PatientAllergyController y se consulta antes que el manejador global.
 */
@RestControllerAdvice(assignableTypes = PatientAllergyController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class PatientAllergyExceptionHandler {

    @ExceptionHandler(PatientAllergyNotFoundApplicationException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(PatientAllergyNotFoundApplicationException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(404, "Not Found", ex.getMessage()));
    }
}
