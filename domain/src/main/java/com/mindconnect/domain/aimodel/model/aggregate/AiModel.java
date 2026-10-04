package com.mindconnect.domain.aimodel.model.aggregate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import com.mindconnect.domain.common.model.AggregateRoot;
import com.mindconnect.domain.common.validation.DomainValidations;
import com.mindconnect.domain.aimodel.event.AiModelRegisteredEvent;
import com.mindconnect.domain.aimodel.event.AiModelUpdatedEvent;
import com.mindconnect.domain.aimodel.model.valueobject.AiModelId;

/**
 * Agregado raíz del contexto aimodel: representa la tabla ai_models.
 *
 * <p>Relaciones (se guardan solo por id, no como objetos):</p>
 * <ul>
 *   <li>providerModelId -> ProviderModelAi</li>
 * </ul>
 *
 * <p>El id y las fechas los pone el propio agregado. No usa Spring ni JPA.</p>
 */
public class AiModel extends AggregateRoot {

    private final AiModelId id;
    private UUID providerModelId;
    private String nameModel;
    private String modelKey;
    private BigDecimal inputTokenPrice;
    private BigDecimal outputTokenPrice;
    private Integer maxTokens;
    private Integer contextWindow;
    private boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private AiModel(
            AiModelId id,
            UUID providerModelId,
            String nameModel,
            String modelKey,
            BigDecimal inputTokenPrice,
            BigDecimal outputTokenPrice,
            Integer maxTokens,
            Integer contextWindow,
            boolean isActive,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        DomainValidations.required(providerModelId, "providerModelId");
        DomainValidations.required(nameModel, "nameModel");
        DomainValidations.required(modelKey, "modelKey");
        DomainValidations.required(inputTokenPrice, "inputTokenPrice");
        DomainValidations.required(outputTokenPrice, "outputTokenPrice");
        DomainValidations.required(maxTokens, "maxTokens");
        DomainValidations.required(contextWindow, "contextWindow");
        DomainValidations.required(createdAt, "createdAt");
        DomainValidations.required(updatedAt, "updatedAt");
        this.providerModelId = providerModelId;
        this.nameModel = nameModel;
        this.modelKey = modelKey;
        this.inputTokenPrice = inputTokenPrice;
        this.outputTokenPrice = outputTokenPrice;
        this.maxTokens = maxTokens;
        this.contextWindow = contextWindow;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /** Crea un registro nuevo: genera el id y las fechas, y deja el evento de registro. */
    public static AiModel register(
            UUID providerModelId,
            String nameModel,
            String modelKey,
            BigDecimal inputTokenPrice,
            BigDecimal outputTokenPrice,
            Integer maxTokens,
            Integer contextWindow) {
        AiModelId id = AiModelId.generate();
        LocalDateTime now = LocalDateTime.now();
        AiModel aggregate = new AiModel(id, providerModelId, nameModel, modelKey, inputTokenPrice, outputTokenPrice, maxTokens, contextWindow, true, now, now);
        aggregate.recordEvent(new AiModelRegisteredEvent(id, now));
        return aggregate;
    }

    /** Reconstruye un registro que ya existía (viene de la base de datos). No genera eventos. */
    public static AiModel restore(
            AiModelId id,
            UUID providerModelId,
            String nameModel,
            String modelKey,
            BigDecimal inputTokenPrice,
            BigDecimal outputTokenPrice,
            Integer maxTokens,
            Integer contextWindow,
            boolean isActive,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new AiModel(id, providerModelId, nameModel, modelKey, inputTokenPrice, outputTokenPrice, maxTokens, contextWindow, isActive, createdAt, updatedAt);
    }

    /** Cambia los datos del registro, actualiza la fecha de modificación y deja el evento. */
    public void update(
            UUID providerModelId,
            String nameModel,
            String modelKey,
            BigDecimal inputTokenPrice,
            BigDecimal outputTokenPrice,
            Integer maxTokens,
            Integer contextWindow) {
        DomainValidations.required(providerModelId, "providerModelId");
        DomainValidations.required(nameModel, "nameModel");
        DomainValidations.required(modelKey, "modelKey");
        DomainValidations.required(inputTokenPrice, "inputTokenPrice");
        DomainValidations.required(outputTokenPrice, "outputTokenPrice");
        DomainValidations.required(maxTokens, "maxTokens");
        DomainValidations.required(contextWindow, "contextWindow");
        this.providerModelId = providerModelId;
        this.nameModel = nameModel;
        this.modelKey = modelKey;
        this.inputTokenPrice = inputTokenPrice;
        this.outputTokenPrice = outputTokenPrice;
        this.maxTokens = maxTokens;
        this.contextWindow = contextWindow;
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        recordEvent(new AiModelUpdatedEvent(this.id, now));
    }

    public AiModelId id() { return id; }
    public UUID providerModelId() { return providerModelId; }
    public String nameModel() { return nameModel; }
    public String modelKey() { return modelKey; }
    public BigDecimal inputTokenPrice() { return inputTokenPrice; }
    public BigDecimal outputTokenPrice() { return outputTokenPrice; }
    public Integer maxTokens() { return maxTokens; }
    public Integer contextWindow() { return contextWindow; }
    public boolean isActive() { return isActive; }
    public LocalDateTime createdAt() { return createdAt; }
    public LocalDateTime updatedAt() { return updatedAt; }
}
