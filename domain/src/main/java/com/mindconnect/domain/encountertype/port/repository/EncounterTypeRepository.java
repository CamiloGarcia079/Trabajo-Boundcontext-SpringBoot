package com.mindconnect.domain.encountertype.port.repository;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.encountertype.model.aggregate.EncounterType;
import com.mindconnect.domain.encountertype.model.valueobject.EncounterTypeId;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y buscar EncounterType.
 * Lo implementa la infraestructura.
 */
public interface EncounterTypeRepository {

    EncounterType save(EncounterType aggregate);

    Optional<EncounterType> findById(EncounterTypeId id);

    List<EncounterType> findAll();

    void delete(EncounterType aggregate);
}
