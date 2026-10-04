package com.mindconnect.application.common.exception;

/**
 * Error propio de la capa de aplicación (por ejemplo, un registro que no existe).
 */
public abstract class ApplicationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    protected ApplicationException(String message) {
        super(message);
    }
}
