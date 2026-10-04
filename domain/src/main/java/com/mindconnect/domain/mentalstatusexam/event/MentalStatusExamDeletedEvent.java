package com.mindconnect.domain.mentalstatusexam.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.mentalstatusexam.model.valueobject.MentalStatusExamId;

/**
 * Evento de dominio: se eliminó un registro de mental_status_exams.
 */
public record MentalStatusExamDeletedEvent(
        MentalStatusExamId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
