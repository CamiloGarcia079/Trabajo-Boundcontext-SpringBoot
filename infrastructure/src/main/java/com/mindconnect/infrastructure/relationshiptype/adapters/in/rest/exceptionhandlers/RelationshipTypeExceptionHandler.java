package com.mindconnect.infrastructure.relationshiptype.adapters.in.rest.exceptionhandlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindconnect.application.relationshiptype.exception.RelationshipTypeNotFoundApplicationException;
import com.mindconnect.infrastructure.relationshiptype.adapters.in.rest.controllers.RelationshipTypeController;
import com.mindconnect.infrastructure.common.dtos.ErrorResponse;

/**
 * Convierte el "no encontrado" de relationship_types en una respuesta 404.
 * Solo aplica a RelationshipTypeController y se consulta antes que el manejador global.
 */
@RestControllerAdvice(assignableTypes = RelationshipTypeController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RelationshipTypeExceptionHandler {

    @ExceptionHandler(RelationshipTypeNotFoundApplicationException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(RelationshipTypeNotFoundApplicationException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(404, "Not Found", ex.getMessage()));
    }
}
