package com.mindconnect.application.aimodel.command;

import java.math.BigDecimal;
import java.util.UUID;

import com.mindconnect.domain.aimodel.model.valueobject.AiModelId;

public record UpdateAiModelCommand(
        AiModelId id,
        UUID providerModelId,
        String nameModel,
        String modelKey,
        BigDecimal inputTokenPrice,
        BigDecimal outputTokenPrice,
        Integer maxTokens,
        Integer contextWindow
) {
}
