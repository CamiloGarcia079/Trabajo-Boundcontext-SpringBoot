package com.mindconnect.application.citymunicipality.exception;

import com.mindconnect.application.common.exception.ApplicationException;
import com.mindconnect.domain.citymunicipality.model.valueobject.CityMunicipalityId;

public class CityMunicipalityNotFoundApplicationException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public CityMunicipalityNotFoundApplicationException(CityMunicipalityId id) {
        super("CityMunicipality with id " + id.value() + " was not found.");
    }
}
