package com.mindconnect.application.chatairunmetric.exception;

import com.mindconnect.application.common.exception.ApplicationException;
import com.mindconnect.domain.chatairunmetric.model.valueobject.ChatAiRunMetricId;

public class ChatAiRunMetricNotFoundApplicationException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public ChatAiRunMetricNotFoundApplicationException(ChatAiRunMetricId id) {
        super("ChatAiRunMetric with id " + id.value() + " was not found.");
    }
}
