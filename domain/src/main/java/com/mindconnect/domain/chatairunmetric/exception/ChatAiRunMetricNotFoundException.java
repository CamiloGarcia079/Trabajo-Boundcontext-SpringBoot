package com.mindconnect.domain.chatairunmetric.exception;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.chatairunmetric.model.valueobject.ChatAiRunMetricId;

public class ChatAiRunMetricNotFoundException extends DomainException {

    private static final long serialVersionUID = 1L;

    public ChatAiRunMetricNotFoundException(ChatAiRunMetricId id) {
        super("ChatAiRunMetric with id " + id.value() + " was not found.");
    }
}
