package com.mindconnect.application.country.exception;

import com.mindconnect.application.common.exception.ApplicationException;
import com.mindconnect.domain.country.model.valueobject.CountryId;

public class CountryNotFoundApplicationException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public CountryNotFoundApplicationException(CountryId id) {
        super("Country with id " + id.value() + " was not found.");
    }
}
