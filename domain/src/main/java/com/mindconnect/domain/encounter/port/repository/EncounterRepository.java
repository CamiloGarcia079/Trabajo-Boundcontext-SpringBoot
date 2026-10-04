package com.mindconnect.domain.encounter.port.repository;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.encounter.model.aggregate.Encounter;
import com.mindconnect.domain.encounter.model.valueobject.EncounterId;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y buscar Encounter.
 * Lo implementa la infraestructura.
 */
public interface EncounterRepository {

    Encounter save(Encounter aggregate);

    Optional<Encounter> findById(EncounterId id);

    List<Encounter> findAll();

    void delete(Encounter aggregate);
}
