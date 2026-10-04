package com.mindconnect.infrastructure.clinicalrecord.adapters.in.rest.exceptionhandlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindconnect.application.clinicalrecord.exception.ClinicalRecordNotFoundApplicationException;
import com.mindconnect.infrastructure.clinicalrecord.adapters.in.rest.controllers.ClinicalRecordController;
import com.mindconnect.infrastructure.common.dtos.ErrorResponse;

/**
 * Convierte el "no encontrado" de clinical_records en una respuesta 404.
 * Solo aplica a ClinicalRecordController y se consulta antes que el manejador global.
 */
@RestControllerAdvice(assignableTypes = ClinicalRecordController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ClinicalRecordExceptionHandler {

    @ExceptionHandler(ClinicalRecordNotFoundApplicationException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ClinicalRecordNotFoundApplicationException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(404, "Not Found", ex.getMessage()));
    }
}
