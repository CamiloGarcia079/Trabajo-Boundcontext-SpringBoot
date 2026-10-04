package com.mindconnect.application.chatairunmetric.command;

import java.math.BigDecimal;
import java.util.UUID;

public record RegisterChatAiRunMetricCommand(
        UUID aiRunId,
        Integer promptTokens,
        Integer completionTokens,
        Integer totalTokens,
        BigDecimal cost
) {
}
