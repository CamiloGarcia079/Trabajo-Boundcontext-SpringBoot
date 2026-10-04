package com.mindconnect.domain.study.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.study.model.valueobject.StudyId;

/**
 * Evento de dominio: se registró un registro de studies.
 */
public record StudyRegisteredEvent(
        StudyId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
