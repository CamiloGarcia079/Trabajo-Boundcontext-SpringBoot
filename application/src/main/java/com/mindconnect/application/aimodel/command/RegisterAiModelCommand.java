package com.mindconnect.application.aimodel.command;

import java.math.BigDecimal;
import java.util.UUID;

public record RegisterAiModelCommand(
        UUID providerModelId,
        String nameModel,
        String modelKey,
        BigDecimal inputTokenPrice,
        BigDecimal outputTokenPrice,
        Integer maxTokens,
        Integer contextWindow
) {
}
