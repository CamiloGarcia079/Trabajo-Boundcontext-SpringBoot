package com.mindconnect.application.chatconversationaisetting.usecase;

import com.mindconnect.application.chatconversationaisetting.command.RegisterChatConversationAiSettingCommand;
import com.mindconnect.application.chatconversationaisetting.dto.ChatConversationAiSettingResponse;
import com.mindconnect.domain.chatconversationaisetting.model.aggregate.ChatConversationAiSetting;
import com.mindconnect.domain.chatconversationaisetting.port.repository.ChatConversationAiSettingRepository;

public class RegisterChatConversationAiSettingUseCase {

    private final ChatConversationAiSettingRepository repository;

    public RegisterChatConversationAiSettingUseCase(ChatConversationAiSettingRepository repository) {
        this.repository = repository;
    }

    public ChatConversationAiSettingResponse execute(RegisterChatConversationAiSettingCommand command) {
        ChatConversationAiSetting aggregate = ChatConversationAiSetting.register(
                command.conversationId(), command.aiEnabled(), command.defaultModelId());
        ChatConversationAiSetting saved = repository.save(aggregate);
        return ChatConversationAiSettingResponse.fromDomain(saved);
    }
}
