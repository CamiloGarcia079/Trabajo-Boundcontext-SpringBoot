package com.mindconnect.application.aimodel.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.aimodel.model.aggregate.AiModel;

public record AiModelResponse(
        UUID id,
        UUID providerModelId,
        String nameModel,
        String modelKey,
        BigDecimal inputTokenPrice,
        BigDecimal outputTokenPrice,
        Integer maxTokens,
        Integer contextWindow,
        boolean isActive,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public static AiModelResponse fromDomain(AiModel aggregate) {
        return new AiModelResponse(
                aggregate.id().value(),
                aggregate.providerModelId(), aggregate.nameModel(), aggregate.modelKey(), aggregate.inputTokenPrice(), aggregate.outputTokenPrice(), aggregate.maxTokens(), aggregate.contextWindow(), aggregate.isActive(), aggregate.createdAt(), aggregate.updatedAt());
    }
}
