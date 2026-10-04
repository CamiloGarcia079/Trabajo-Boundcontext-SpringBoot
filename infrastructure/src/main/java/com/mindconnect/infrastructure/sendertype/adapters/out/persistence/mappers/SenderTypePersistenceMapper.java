package com.mindconnect.infrastructure.sendertype.adapters.out.persistence.mappers;

import com.mindconnect.domain.sendertype.model.aggregate.SenderType;
import com.mindconnect.domain.sendertype.model.valueobject.SenderTypeId;
import com.mindconnect.infrastructure.sendertype.adapters.out.persistence.entity.SenderTypeJpaEntity;

/**
 * Convierte entre el agregado de dominio y la entidad JPA de sender_types.
 */
public class SenderTypePersistenceMapper {

    public SenderTypeJpaEntity toJpa(SenderType domain) {
        if (domain == null) {
            return null;
        }

        SenderTypeJpaEntity jpa = new SenderTypeJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setNameType(domain.nameType());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());

        return jpa;
    }

    public SenderType toDomain(SenderTypeJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }

        return SenderType.restore(
                new SenderTypeId(jpa.getId()),
                jpa.getNameType(), jpa.getCreatedAt(), jpa.getUpdatedAt());
    }
}
