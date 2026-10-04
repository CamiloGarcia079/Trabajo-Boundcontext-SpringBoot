package com.mindconnect.application.chatconversationaisetting.usecase;

import java.time.LocalDateTime;

import com.mindconnect.application.chatconversationaisetting.exception.ChatConversationAiSettingNotFoundApplicationException;
import com.mindconnect.domain.chatconversationaisetting.event.ChatConversationAiSettingDeletedEvent;
import com.mindconnect.domain.chatconversationaisetting.model.valueobject.ChatConversationAiSettingId;
import com.mindconnect.domain.chatconversationaisetting.port.repository.ChatConversationAiSettingRepository;

public class DeleteChatConversationAiSettingUseCase {

    private final ChatConversationAiSettingRepository repository;

    public DeleteChatConversationAiSettingUseCase(ChatConversationAiSettingRepository repository) {
        this.repository = repository;
    }

    public ChatConversationAiSettingDeletedEvent execute(ChatConversationAiSettingId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ChatConversationAiSettingNotFoundApplicationException(id));

        repository.delete(aggregate);

        return new ChatConversationAiSettingDeletedEvent(id, LocalDateTime.now());
    }
}
