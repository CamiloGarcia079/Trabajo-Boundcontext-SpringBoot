package com.mindconnect.domain.treatmentgoalstatus.port.repository;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.treatmentgoalstatus.model.aggregate.TreatmentGoalStatus;
import com.mindconnect.domain.treatmentgoalstatus.model.valueobject.TreatmentGoalStatusId;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y buscar TreatmentGoalStatus.
 * Lo implementa la infraestructura.
 */
public interface TreatmentGoalStatusRepository {

    TreatmentGoalStatus save(TreatmentGoalStatus aggregate);

    Optional<TreatmentGoalStatus> findById(TreatmentGoalStatusId id);

    List<TreatmentGoalStatus> findAll();

    void delete(TreatmentGoalStatus aggregate);
}
