package com.mindconnect.infrastructure.messagetype.adapters.out.persistence.mappers;

import com.mindconnect.domain.messagetype.model.aggregate.MessageType;
import com.mindconnect.domain.messagetype.model.valueobject.MessageTypeId;
import com.mindconnect.infrastructure.messagetype.adapters.out.persistence.entity.MessageTypeJpaEntity;

/**
 * Convierte entre el agregado de dominio y la entidad JPA de message_types.
 */
public class MessageTypePersistenceMapper {

    public MessageTypeJpaEntity toJpa(MessageType domain) {
        if (domain == null) {
            return null;
        }

        MessageTypeJpaEntity jpa = new MessageTypeJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setNameType(domain.nameType());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());

        return jpa;
    }

    public MessageType toDomain(MessageTypeJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }

        return MessageType.restore(
                new MessageTypeId(jpa.getId()),
                jpa.getNameType(), jpa.getCreatedAt(), jpa.getUpdatedAt());
    }
}
