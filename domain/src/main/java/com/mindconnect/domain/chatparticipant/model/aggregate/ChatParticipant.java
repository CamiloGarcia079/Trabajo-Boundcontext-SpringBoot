package com.mindconnect.domain.chatparticipant.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import com.mindconnect.domain.common.model.AggregateRoot;
import com.mindconnect.domain.common.validation.DomainValidations;
import com.mindconnect.domain.chatparticipant.event.ChatParticipantRegisteredEvent;
import com.mindconnect.domain.chatparticipant.event.ChatParticipantUpdatedEvent;
import com.mindconnect.domain.chatparticipant.model.valueobject.ChatParticipantId;

/**
 * Agregado raíz del contexto chatparticipant: representa la tabla chat_participants.
 *
 * <p>Relaciones (se guardan solo por id, no como objetos):</p>
 * <ul>
 *   <li>conversationId -> ChatConversation</li>
 *   <li>participantTypeId -> SenderType</li>
 *   <li>patientId -> Patient</li>
 *   <li>professionalId -> Professional</li>
 * </ul>
 *
 * <p>El id y las fechas los pone el propio agregado. No usa Spring ni JPA.</p>
 */
public class ChatParticipant extends AggregateRoot {

    private final ChatParticipantId id;
    private UUID conversationId;
    private UUID participantTypeId;
    private UUID patientId;
    private UUID professionalId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private ChatParticipant(
            ChatParticipantId id,
            UUID conversationId,
            UUID participantTypeId,
            UUID patientId,
            UUID professionalId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        DomainValidations.required(conversationId, "conversationId");
        DomainValidations.required(participantTypeId, "participantTypeId");
        DomainValidations.required(createdAt, "createdAt");
        DomainValidations.required(updatedAt, "updatedAt");
        this.conversationId = conversationId;
        this.participantTypeId = participantTypeId;
        this.patientId = patientId;
        this.professionalId = professionalId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /** Crea un registro nuevo: genera el id y las fechas, y deja el evento de registro. */
    public static ChatParticipant register(
            UUID conversationId,
            UUID participantTypeId,
            UUID patientId,
            UUID professionalId) {
        ChatParticipantId id = ChatParticipantId.generate();
        LocalDateTime now = LocalDateTime.now();
        ChatParticipant aggregate = new ChatParticipant(id, conversationId, participantTypeId, patientId, professionalId, now, now);
        aggregate.recordEvent(new ChatParticipantRegisteredEvent(id, now));
        return aggregate;
    }

    /** Reconstruye un registro que ya existía (viene de la base de datos). No genera eventos. */
    public static ChatParticipant restore(
            ChatParticipantId id,
            UUID conversationId,
            UUID participantTypeId,
            UUID patientId,
            UUID professionalId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new ChatParticipant(id, conversationId, participantTypeId, patientId, professionalId, createdAt, updatedAt);
    }

    /** Cambia los datos del registro, actualiza la fecha de modificación y deja el evento. */
    public void update(
            UUID conversationId,
            UUID participantTypeId,
            UUID patientId,
            UUID professionalId) {
        DomainValidations.required(conversationId, "conversationId");
        DomainValidations.required(participantTypeId, "participantTypeId");
        this.conversationId = conversationId;
        this.participantTypeId = participantTypeId;
        this.patientId = patientId;
        this.professionalId = professionalId;
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        recordEvent(new ChatParticipantUpdatedEvent(this.id, now));
    }

    public ChatParticipantId id() { return id; }
    public UUID conversationId() { return conversationId; }
    public UUID participantTypeId() { return participantTypeId; }
    public UUID patientId() { return patientId; }
    public UUID professionalId() { return professionalId; }
    public LocalDateTime createdAt() { return createdAt; }
    public LocalDateTime updatedAt() { return updatedAt; }
}
