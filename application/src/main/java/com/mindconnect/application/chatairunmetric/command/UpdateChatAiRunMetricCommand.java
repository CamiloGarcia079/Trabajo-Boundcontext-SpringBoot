package com.mindconnect.application.chatairunmetric.command;

import java.math.BigDecimal;
import java.util.UUID;

import com.mindconnect.domain.chatairunmetric.model.valueobject.ChatAiRunMetricId;

public record UpdateChatAiRunMetricCommand(
        ChatAiRunMetricId id,
        UUID aiRunId,
        Integer promptTokens,
        Integer completionTokens,
        Integer totalTokens,
        BigDecimal cost
) {
}
