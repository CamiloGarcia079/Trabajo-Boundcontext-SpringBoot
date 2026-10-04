package com.mindconnect.application.chatconversationaisetting.usecase;

import com.mindconnect.application.chatconversationaisetting.command.UpdateChatConversationAiSettingCommand;
import com.mindconnect.application.chatconversationaisetting.dto.ChatConversationAiSettingResponse;
import com.mindconnect.application.chatconversationaisetting.exception.ChatConversationAiSettingNotFoundApplicationException;
import com.mindconnect.domain.chatconversationaisetting.model.aggregate.ChatConversationAiSetting;
import com.mindconnect.domain.chatconversationaisetting.port.repository.ChatConversationAiSettingRepository;

public class UpdateChatConversationAiSettingUseCase {

    private final ChatConversationAiSettingRepository repository;

    public UpdateChatConversationAiSettingUseCase(ChatConversationAiSettingRepository repository) {
        this.repository = repository;
    }

    public ChatConversationAiSettingResponse execute(UpdateChatConversationAiSettingCommand command) {
        ChatConversationAiSetting aggregate = repository.findById(command.id())
                .orElseThrow(() -> new ChatConversationAiSettingNotFoundApplicationException(command.id()));
        aggregate.update(
                command.conversationId(), command.aiEnabled(), command.defaultModelId());
        ChatConversationAiSetting saved = repository.save(aggregate);
        return ChatConversationAiSettingResponse.fromDomain(saved);
    }
}
