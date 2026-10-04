package com.mindconnect.domain.chatconversationaisetting.exception;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.chatconversationaisetting.model.valueobject.ChatConversationAiSettingId;

public class ChatConversationAiSettingNotFoundException extends DomainException {

    private static final long serialVersionUID = 1L;

    public ChatConversationAiSettingNotFoundException(ChatConversationAiSettingId id) {
        super("ChatConversationAiSetting with id " + id.value() + " was not found.");
    }
}
