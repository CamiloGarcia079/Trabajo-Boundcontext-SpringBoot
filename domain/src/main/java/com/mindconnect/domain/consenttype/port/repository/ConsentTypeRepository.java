package com.mindconnect.domain.consenttype.port.repository;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.consenttype.model.aggregate.ConsentType;
import com.mindconnect.domain.consenttype.model.valueobject.ConsentTypeId;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y buscar ConsentType.
 * Lo implementa la infraestructura.
 */
public interface ConsentTypeRepository {

    ConsentType save(ConsentType aggregate);

    Optional<ConsentType> findById(ConsentTypeId id);

    List<ConsentType> findAll();

    void delete(ConsentType aggregate);
}
