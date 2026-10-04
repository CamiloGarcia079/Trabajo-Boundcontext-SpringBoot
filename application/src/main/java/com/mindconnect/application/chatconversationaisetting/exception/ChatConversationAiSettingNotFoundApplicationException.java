package com.mindconnect.application.chatconversationaisetting.exception;

import com.mindconnect.application.common.exception.ApplicationException;
import com.mindconnect.domain.chatconversationaisetting.model.valueobject.ChatConversationAiSettingId;

public class ChatConversationAiSettingNotFoundApplicationException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public ChatConversationAiSettingNotFoundApplicationException(ChatConversationAiSettingId id) {
        super("ChatConversationAiSetting with id " + id.value() + " was not found.");
    }
}
