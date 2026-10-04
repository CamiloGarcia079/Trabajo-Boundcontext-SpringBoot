package com.mindconnect.domain.chatairunerror.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import com.mindconnect.domain.common.model.AggregateRoot;
import com.mindconnect.domain.common.validation.DomainValidations;
import com.mindconnect.domain.chatairunerror.event.ChatAiRunErrorRegisteredEvent;
import com.mindconnect.domain.chatairunerror.event.ChatAiRunErrorUpdatedEvent;
import com.mindconnect.domain.chatairunerror.model.valueobject.ChatAiRunErrorId;

/**
 * Agregado raíz del contexto chatairunerror: representa la tabla chat_ai_run_errors.
 *
 * <p>Relaciones (se guardan solo por id, no como objetos):</p>
 * <ul>
 *   <li>aiRunId -> ChatAiRun</li>
 * </ul>
 *
 * <p>El id y las fechas los pone el propio agregado. No usa Spring ni JPA.</p>
 */
public class ChatAiRunError extends AggregateRoot {

    private final ChatAiRunErrorId id;
    private UUID aiRunId;
    private String errorMessage;
    private String errorCode;
    private String providerErrorId;
    private LocalDateTime createdAt;

    private ChatAiRunError(
            ChatAiRunErrorId id,
            UUID aiRunId,
            String errorMessage,
            String errorCode,
            String providerErrorId,
            LocalDateTime createdAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        DomainValidations.required(aiRunId, "aiRunId");
        DomainValidations.required(errorMessage, "errorMessage");
        DomainValidations.required(errorCode, "errorCode");
        DomainValidations.required(providerErrorId, "providerErrorId");
        DomainValidations.required(createdAt, "createdAt");
        this.aiRunId = aiRunId;
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.providerErrorId = providerErrorId;
        this.createdAt = createdAt;
    }

    /** Crea un registro nuevo: genera el id y las fechas, y deja el evento de registro. */
    public static ChatAiRunError register(
            UUID aiRunId,
            String errorMessage,
            String errorCode,
            String providerErrorId) {
        ChatAiRunErrorId id = ChatAiRunErrorId.generate();
        LocalDateTime now = LocalDateTime.now();
        ChatAiRunError aggregate = new ChatAiRunError(id, aiRunId, errorMessage, errorCode, providerErrorId, now);
        aggregate.recordEvent(new ChatAiRunErrorRegisteredEvent(id, now));
        return aggregate;
    }

    /** Reconstruye un registro que ya existía (viene de la base de datos). No genera eventos. */
    public static ChatAiRunError restore(
            ChatAiRunErrorId id,
            UUID aiRunId,
            String errorMessage,
            String errorCode,
            String providerErrorId,
            LocalDateTime createdAt) {
        return new ChatAiRunError(id, aiRunId, errorMessage, errorCode, providerErrorId, createdAt);
    }

    /** Cambia los datos del registro, actualiza la fecha de modificación y deja el evento. */
    public void update(
            UUID aiRunId,
            String errorMessage,
            String errorCode,
            String providerErrorId) {
        DomainValidations.required(aiRunId, "aiRunId");
        DomainValidations.required(errorMessage, "errorMessage");
        DomainValidations.required(errorCode, "errorCode");
        DomainValidations.required(providerErrorId, "providerErrorId");
        this.aiRunId = aiRunId;
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.providerErrorId = providerErrorId;
        LocalDateTime now = LocalDateTime.now();
        recordEvent(new ChatAiRunErrorUpdatedEvent(this.id, now));
    }

    public ChatAiRunErrorId id() { return id; }
    public UUID aiRunId() { return aiRunId; }
    public String errorMessage() { return errorMessage; }
    public String errorCode() { return errorCode; }
    public String providerErrorId() { return providerErrorId; }
    public LocalDateTime createdAt() { return createdAt; }
}
