package com.mindconnect.domain.treatmentgoal.port.repository;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.treatmentgoal.model.aggregate.TreatmentGoal;
import com.mindconnect.domain.treatmentgoal.model.valueobject.TreatmentGoalId;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y buscar TreatmentGoal.
 * Lo implementa la infraestructura.
 */
public interface TreatmentGoalRepository {

    TreatmentGoal save(TreatmentGoal aggregate);

    Optional<TreatmentGoal> findById(TreatmentGoalId id);

    List<TreatmentGoal> findAll();

    void delete(TreatmentGoal aggregate);
}
