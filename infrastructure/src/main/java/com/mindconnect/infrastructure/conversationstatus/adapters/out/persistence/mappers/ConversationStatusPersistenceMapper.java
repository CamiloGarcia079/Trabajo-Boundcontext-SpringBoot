package com.mindconnect.infrastructure.conversationstatus.adapters.out.persistence.mappers;

import com.mindconnect.domain.conversationstatus.model.aggregate.ConversationStatus;
import com.mindconnect.domain.conversationstatus.model.valueobject.ConversationStatusId;
import com.mindconnect.infrastructure.conversationstatus.adapters.out.persistence.entity.ConversationStatusJpaEntity;

/**
 * Convierte entre el agregado de dominio y la entidad JPA de conversations_statuses.
 */
public class ConversationStatusPersistenceMapper {

    public ConversationStatusJpaEntity toJpa(ConversationStatus domain) {
        if (domain == null) {
            return null;
        }

        ConversationStatusJpaEntity jpa = new ConversationStatusJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setNameStatus(domain.nameStatus());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());

        return jpa;
    }

    public ConversationStatus toDomain(ConversationStatusJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }

        return ConversationStatus.restore(
                new ConversationStatusId(jpa.getId()),
                jpa.getNameStatus(), jpa.getCreatedAt(), jpa.getUpdatedAt());
    }
}
