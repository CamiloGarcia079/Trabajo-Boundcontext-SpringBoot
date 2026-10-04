package com.mindconnect.infrastructure.treatmentgoalstatus.adapters.in.rest.exceptionhandlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindconnect.application.treatmentgoalstatus.exception.TreatmentGoalStatusNotFoundApplicationException;
import com.mindconnect.infrastructure.treatmentgoalstatus.adapters.in.rest.controllers.TreatmentGoalStatusController;
import com.mindconnect.infrastructure.common.dtos.ErrorResponse;

/**
 * Convierte el "no encontrado" de treatment_goal_statuses en una respuesta 404.
 * Solo aplica a TreatmentGoalStatusController y se consulta antes que el manejador global.
 */
@RestControllerAdvice(assignableTypes = TreatmentGoalStatusController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TreatmentGoalStatusExceptionHandler {

    @ExceptionHandler(TreatmentGoalStatusNotFoundApplicationException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(TreatmentGoalStatusNotFoundApplicationException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(404, "Not Found", ex.getMessage()));
    }
}
