package com.mindconnect.domain.country.exception;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.country.model.valueobject.CountryId;

public class CountryNotFoundException extends DomainException {

    private static final long serialVersionUID = 1L;

    public CountryNotFoundException(CountryId id) {
        super("Country with id " + id.value() + " was not found.");
    }
}
