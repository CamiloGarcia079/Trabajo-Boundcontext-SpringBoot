package com.mindconnect.domain.risklevel.port.repository;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.risklevel.model.aggregate.RiskLevel;
import com.mindconnect.domain.risklevel.model.valueobject.RiskLevelId;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y buscar RiskLevel.
 * Lo implementa la infraestructura.
 */
public interface RiskLevelRepository {

    RiskLevel save(RiskLevel aggregate);

    Optional<RiskLevel> findById(RiskLevelId id);

    List<RiskLevel> findAll();

    void delete(RiskLevel aggregate);
}
