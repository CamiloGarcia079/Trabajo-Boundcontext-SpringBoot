package com.mindconnect.domain.chatescalationassignment.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import com.mindconnect.domain.common.model.AggregateRoot;
import com.mindconnect.domain.common.validation.DomainValidations;
import com.mindconnect.domain.chatescalationassignment.event.ChatEscalationAssignmentRegisteredEvent;
import com.mindconnect.domain.chatescalationassignment.event.ChatEscalationAssignmentUpdatedEvent;
import com.mindconnect.domain.chatescalationassignment.model.valueobject.ChatEscalationAssignmentId;

/**
 * Agregado raíz del contexto chatescalationassignment: representa la tabla chat_escalation_assignments.
 *
 * <p>Relaciones (se guardan solo por id, no como objetos):</p>
 * <ul>
 *   <li>escalationId -> ChatEscalation</li>
 *   <li>professionalId -> Professional</li>
 * </ul>
 *
 * <p>El id y las fechas los pone el propio agregado. No usa Spring ni JPA.</p>
 */
public class ChatEscalationAssignment extends AggregateRoot {

    private final ChatEscalationAssignmentId id;
    private UUID escalationId;
    private UUID professionalId;
    private LocalDateTime assignedAt;

    private ChatEscalationAssignment(
            ChatEscalationAssignmentId id,
            UUID escalationId,
            UUID professionalId,
            LocalDateTime assignedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        DomainValidations.required(escalationId, "escalationId");
        DomainValidations.required(professionalId, "professionalId");
        DomainValidations.required(assignedAt, "assignedAt");
        this.escalationId = escalationId;
        this.professionalId = professionalId;
        this.assignedAt = assignedAt;
    }

    /** Crea un registro nuevo: genera el id y las fechas, y deja el evento de registro. */
    public static ChatEscalationAssignment register(
            UUID escalationId,
            UUID professionalId,
            LocalDateTime assignedAt) {
        ChatEscalationAssignmentId id = ChatEscalationAssignmentId.generate();
        LocalDateTime now = LocalDateTime.now();
        ChatEscalationAssignment aggregate = new ChatEscalationAssignment(id, escalationId, professionalId, assignedAt);
        aggregate.recordEvent(new ChatEscalationAssignmentRegisteredEvent(id, now));
        return aggregate;
    }

    /** Reconstruye un registro que ya existía (viene de la base de datos). No genera eventos. */
    public static ChatEscalationAssignment restore(
            ChatEscalationAssignmentId id,
            UUID escalationId,
            UUID professionalId,
            LocalDateTime assignedAt) {
        return new ChatEscalationAssignment(id, escalationId, professionalId, assignedAt);
    }

    /** Cambia los datos del registro, actualiza la fecha de modificación y deja el evento. */
    public void update(
            UUID escalationId,
            UUID professionalId,
            LocalDateTime assignedAt) {
        DomainValidations.required(escalationId, "escalationId");
        DomainValidations.required(professionalId, "professionalId");
        DomainValidations.required(assignedAt, "assignedAt");
        this.escalationId = escalationId;
        this.professionalId = professionalId;
        this.assignedAt = assignedAt;
        LocalDateTime now = LocalDateTime.now();
        recordEvent(new ChatEscalationAssignmentUpdatedEvent(this.id, now));
    }

    public ChatEscalationAssignmentId id() { return id; }
    public UUID escalationId() { return escalationId; }
    public UUID professionalId() { return professionalId; }
    public LocalDateTime assignedAt() { return assignedAt; }
}
