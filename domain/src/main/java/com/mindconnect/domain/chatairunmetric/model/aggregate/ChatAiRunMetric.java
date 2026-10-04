package com.mindconnect.domain.chatairunmetric.model.aggregate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import com.mindconnect.domain.common.model.AggregateRoot;
import com.mindconnect.domain.common.validation.DomainValidations;
import com.mindconnect.domain.chatairunmetric.event.ChatAiRunMetricRegisteredEvent;
import com.mindconnect.domain.chatairunmetric.event.ChatAiRunMetricUpdatedEvent;
import com.mindconnect.domain.chatairunmetric.model.valueobject.ChatAiRunMetricId;

/**
 * Agregado raíz del contexto chatairunmetric: representa la tabla chat_ai_run_metrics.
 *
 * <p>Relaciones (se guardan solo por id, no como objetos):</p>
 * <ul>
 *   <li>aiRunId -> ChatAiRun</li>
 * </ul>
 *
 * <p>El id y las fechas los pone el propio agregado. No usa Spring ni JPA.</p>
 */
public class ChatAiRunMetric extends AggregateRoot {

    private final ChatAiRunMetricId id;
    private UUID aiRunId;
    private Integer promptTokens;
    private Integer completionTokens;
    private Integer totalTokens;
    private BigDecimal cost;
    private LocalDateTime createdAt;

    private ChatAiRunMetric(
            ChatAiRunMetricId id,
            UUID aiRunId,
            Integer promptTokens,
            Integer completionTokens,
            Integer totalTokens,
            BigDecimal cost,
            LocalDateTime createdAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        DomainValidations.required(aiRunId, "aiRunId");
        DomainValidations.required(promptTokens, "promptTokens");
        DomainValidations.required(completionTokens, "completionTokens");
        DomainValidations.required(totalTokens, "totalTokens");
        DomainValidations.required(cost, "cost");
        DomainValidations.required(createdAt, "createdAt");
        this.aiRunId = aiRunId;
        this.promptTokens = promptTokens;
        this.completionTokens = completionTokens;
        this.totalTokens = totalTokens;
        this.cost = cost;
        this.createdAt = createdAt;
    }

    /** Crea un registro nuevo: genera el id y las fechas, y deja el evento de registro. */
    public static ChatAiRunMetric register(
            UUID aiRunId,
            Integer promptTokens,
            Integer completionTokens,
            Integer totalTokens,
            BigDecimal cost) {
        ChatAiRunMetricId id = ChatAiRunMetricId.generate();
        LocalDateTime now = LocalDateTime.now();
        ChatAiRunMetric aggregate = new ChatAiRunMetric(id, aiRunId, promptTokens, completionTokens, totalTokens, cost, now);
        aggregate.recordEvent(new ChatAiRunMetricRegisteredEvent(id, now));
        return aggregate;
    }

    /** Reconstruye un registro que ya existía (viene de la base de datos). No genera eventos. */
    public static ChatAiRunMetric restore(
            ChatAiRunMetricId id,
            UUID aiRunId,
            Integer promptTokens,
            Integer completionTokens,
            Integer totalTokens,
            BigDecimal cost,
            LocalDateTime createdAt) {
        return new ChatAiRunMetric(id, aiRunId, promptTokens, completionTokens, totalTokens, cost, createdAt);
    }

    /** Cambia los datos del registro, actualiza la fecha de modificación y deja el evento. */
    public void update(
            UUID aiRunId,
            Integer promptTokens,
            Integer completionTokens,
            Integer totalTokens,
            BigDecimal cost) {
        DomainValidations.required(aiRunId, "aiRunId");
        DomainValidations.required(promptTokens, "promptTokens");
        DomainValidations.required(completionTokens, "completionTokens");
        DomainValidations.required(totalTokens, "totalTokens");
        DomainValidations.required(cost, "cost");
        this.aiRunId = aiRunId;
        this.promptTokens = promptTokens;
        this.completionTokens = completionTokens;
        this.totalTokens = totalTokens;
        this.cost = cost;
        LocalDateTime now = LocalDateTime.now();
        recordEvent(new ChatAiRunMetricUpdatedEvent(this.id, now));
    }

    public ChatAiRunMetricId id() { return id; }
    public UUID aiRunId() { return aiRunId; }
    public Integer promptTokens() { return promptTokens; }
    public Integer completionTokens() { return completionTokens; }
    public Integer totalTokens() { return totalTokens; }
    public BigDecimal cost() { return cost; }
    public LocalDateTime createdAt() { return createdAt; }
}
