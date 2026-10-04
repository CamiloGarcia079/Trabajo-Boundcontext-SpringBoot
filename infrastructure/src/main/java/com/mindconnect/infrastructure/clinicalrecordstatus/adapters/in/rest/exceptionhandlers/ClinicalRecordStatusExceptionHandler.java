package com.mindconnect.infrastructure.clinicalrecordstatus.adapters.in.rest.exceptionhandlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindconnect.application.clinicalrecordstatus.exception.ClinicalRecordStatusNotFoundApplicationException;
import com.mindconnect.infrastructure.clinicalrecordstatus.adapters.in.rest.controllers.ClinicalRecordStatusController;
import com.mindconnect.infrastructure.common.dtos.ErrorResponse;

/**
 * Convierte el "no encontrado" de clinical_record_statuses en una respuesta 404.
 * Solo aplica a ClinicalRecordStatusController y se consulta antes que el manejador global.
 */
@RestControllerAdvice(assignableTypes = ClinicalRecordStatusController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ClinicalRecordStatusExceptionHandler {

    @ExceptionHandler(ClinicalRecordStatusNotFoundApplicationException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ClinicalRecordStatusNotFoundApplicationException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(404, "Not Found", ex.getMessage()));
    }
}
