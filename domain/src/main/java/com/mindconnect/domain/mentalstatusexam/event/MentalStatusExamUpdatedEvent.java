package com.mindconnect.domain.mentalstatusexam.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.mentalstatusexam.model.valueobject.MentalStatusExamId;

/**
 * Evento de dominio: se actualizó un registro de mental_status_exams.
 */
public record MentalStatusExamUpdatedEvent(
        MentalStatusExamId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
