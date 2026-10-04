package com.mindconnect.domain.study.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.study.model.valueobject.StudyId;

/**
 * Evento de dominio: se eliminó un registro de studies.
 */
public record StudyDeletedEvent(
        StudyId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
