package com.mindconnect.application.chatconversationaisetting.usecase;

import java.util.UUID;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.mindconnect.application.chatconversationaisetting.exception.ChatConversationAiSettingNotFoundApplicationException;
import com.mindconnect.domain.chatconversationaisetting.event.ChatConversationAiSettingDeletedEvent;
import com.mindconnect.domain.chatconversationaisetting.model.aggregate.ChatConversationAiSetting;
import com.mindconnect.domain.chatconversationaisetting.model.valueobject.ChatConversationAiSettingId;
import com.mindconnect.domain.chatconversationaisetting.port.repository.ChatConversationAiSettingRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteChatConversationAiSettingUseCaseTest {

    @Test
    void eliminaUnRegistroExistente() {
        ChatConversationAiSetting aggregate = ChatConversationAiSetting.register(UUID.randomUUID(), true, UUID.randomUUID());
        FakeChatConversationAiSettingRepository repository = new FakeChatConversationAiSettingRepository();
        repository.save(aggregate);
        DeleteChatConversationAiSettingUseCase useCase = new DeleteChatConversationAiSettingUseCase(repository);

        ChatConversationAiSettingDeletedEvent event = useCase.execute(aggregate.id());

        assertSame(aggregate, repository.deleted);
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void rechazaEliminarUnRegistroQueNoExiste() {
        FakeChatConversationAiSettingRepository repository = new FakeChatConversationAiSettingRepository();
        DeleteChatConversationAiSettingUseCase useCase = new DeleteChatConversationAiSettingUseCase(repository);

        assertThrows(ChatConversationAiSettingNotFoundApplicationException.class, () -> useCase.execute(ChatConversationAiSettingId.generate()));
        assertNull(repository.deleted);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeChatConversationAiSettingRepository implements ChatConversationAiSettingRepository {
        private final Map<ChatConversationAiSettingId, ChatConversationAiSetting> store = new LinkedHashMap<>();
        private ChatConversationAiSetting deleted;

        @Override
        public ChatConversationAiSetting save(ChatConversationAiSetting aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<ChatConversationAiSetting> findById(ChatConversationAiSettingId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<ChatConversationAiSetting> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(ChatConversationAiSetting aggregate) {
            deleted = aggregate;
            store.remove(aggregate.id());
        }
    }
}
