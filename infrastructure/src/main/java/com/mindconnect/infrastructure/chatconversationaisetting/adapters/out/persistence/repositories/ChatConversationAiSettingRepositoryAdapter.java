package com.mindconnect.infrastructure.chatconversationaisetting.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.chatconversationaisetting.model.aggregate.ChatConversationAiSetting;
import com.mindconnect.domain.chatconversationaisetting.model.valueobject.ChatConversationAiSettingId;
import com.mindconnect.domain.chatconversationaisetting.port.repository.ChatConversationAiSettingRepository;
import com.mindconnect.infrastructure.chatconversationaisetting.adapters.out.persistence.mappers.ChatConversationAiSettingPersistenceMapper;

/**
 * Adaptador: implementa el puerto del dominio usando Spring Data y el mapper.
 */
public class ChatConversationAiSettingRepositoryAdapter implements ChatConversationAiSettingRepository {

    private final ChatConversationAiSettingJpaRepository jpaRepository;
    private final ChatConversationAiSettingPersistenceMapper mapper;

    public ChatConversationAiSettingRepositoryAdapter(ChatConversationAiSettingJpaRepository jpaRepository, ChatConversationAiSettingPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ChatConversationAiSetting save(ChatConversationAiSetting aggregate) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(aggregate)));
    }

    @Override
    public Optional<ChatConversationAiSetting> findById(ChatConversationAiSettingId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<ChatConversationAiSetting> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(ChatConversationAiSetting aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
