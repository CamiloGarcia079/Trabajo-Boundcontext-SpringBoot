package com.mindconnect.domain.riskassessment.port.repository;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.riskassessment.model.aggregate.RiskAssessment;
import com.mindconnect.domain.riskassessment.model.valueobject.RiskAssessmentId;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y buscar RiskAssessment.
 * Lo implementa la infraestructura.
 */
public interface RiskAssessmentRepository {

    RiskAssessment save(RiskAssessment aggregate);

    Optional<RiskAssessment> findById(RiskAssessmentId id);

    List<RiskAssessment> findAll();

    void delete(RiskAssessment aggregate);
}
