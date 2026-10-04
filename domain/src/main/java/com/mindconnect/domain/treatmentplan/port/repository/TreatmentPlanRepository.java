package com.mindconnect.domain.treatmentplan.port.repository;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.treatmentplan.model.aggregate.TreatmentPlan;
import com.mindconnect.domain.treatmentplan.model.valueobject.TreatmentPlanId;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y buscar TreatmentPlan.
 * Lo implementa la infraestructura.
 */
public interface TreatmentPlanRepository {

    TreatmentPlan save(TreatmentPlan aggregate);

    Optional<TreatmentPlan> findById(TreatmentPlanId id);

    List<TreatmentPlan> findAll();

    void delete(TreatmentPlan aggregate);
}
