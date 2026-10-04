package com.mindconnect.application.chatconversationaisetting.usecase;

import com.mindconnect.application.chatconversationaisetting.dto.ChatConversationAiSettingResponse;
import com.mindconnect.application.chatconversationaisetting.exception.ChatConversationAiSettingNotFoundApplicationException;
import com.mindconnect.domain.chatconversationaisetting.model.valueobject.ChatConversationAiSettingId;
import com.mindconnect.domain.chatconversationaisetting.port.repository.ChatConversationAiSettingRepository;

public class GetChatConversationAiSettingByIdUseCase {

    private final ChatConversationAiSettingRepository repository;

    public GetChatConversationAiSettingByIdUseCase(ChatConversationAiSettingRepository repository) {
        this.repository = repository;
    }

    public ChatConversationAiSettingResponse execute(ChatConversationAiSettingId id) {
        return repository.findById(id)
                .map(ChatConversationAiSettingResponse::fromDomain)
                .orElseThrow(() -> new ChatConversationAiSettingNotFoundApplicationException(id));
    }
}
