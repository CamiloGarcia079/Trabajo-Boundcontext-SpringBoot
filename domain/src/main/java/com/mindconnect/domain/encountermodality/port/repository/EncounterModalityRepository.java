package com.mindconnect.domain.encountermodality.port.repository;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.encountermodality.model.aggregate.EncounterModality;
import com.mindconnect.domain.encountermodality.model.valueobject.EncounterModalityId;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y buscar EncounterModality.
 * Lo implementa la infraestructura.
 */
public interface EncounterModalityRepository {

    EncounterModality save(EncounterModality aggregate);

    Optional<EncounterModality> findById(EncounterModalityId id);

    List<EncounterModality> findAll();

    void delete(EncounterModality aggregate);
}
