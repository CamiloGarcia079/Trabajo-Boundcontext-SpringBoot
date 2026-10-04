package com.mindconnect.infrastructure.diagnosticsystem.adapters.out.persistence.mappers;

import com.mindconnect.domain.diagnosticsystem.model.aggregate.DiagnosticSystem;
import com.mindconnect.domain.diagnosticsystem.model.valueobject.DiagnosticSystemId;
import com.mindconnect.infrastructure.diagnosticsystem.adapters.out.persistence.entity.DiagnosticSystemJpaEntity;

/**
 * Convierte entre el agregado de dominio y la entidad JPA de diagnostic_systems.
 */
public class DiagnosticSystemPersistenceMapper {

    public DiagnosticSystemJpaEntity toJpa(DiagnosticSystem domain) {
        if (domain == null) {
            return null;
        }

        DiagnosticSystemJpaEntity jpa = new DiagnosticSystemJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setCode(domain.code());
        jpa.setName(domain.name());
        jpa.setActive(domain.active());
        jpa.setVersion(domain.version());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());

        return jpa;
    }

    public DiagnosticSystem toDomain(DiagnosticSystemJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }

        return DiagnosticSystem.restore(
                new DiagnosticSystemId(jpa.getId()),
                jpa.getCode(), jpa.getName(), jpa.getActive(), jpa.getVersion(), jpa.getCreatedAt(), jpa.getUpdatedAt());
    }
}
