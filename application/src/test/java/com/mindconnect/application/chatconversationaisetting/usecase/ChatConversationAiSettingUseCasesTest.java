package com.mindconnect.application.chatconversationaisetting.usecase;

import java.util.UUID;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.chatconversationaisetting.command.RegisterChatConversationAiSettingCommand;
import com.mindconnect.application.chatconversationaisetting.command.UpdateChatConversationAiSettingCommand;
import com.mindconnect.application.chatconversationaisetting.dto.ChatConversationAiSettingResponse;
import com.mindconnect.application.chatconversationaisetting.exception.ChatConversationAiSettingNotFoundApplicationException;
import com.mindconnect.domain.chatconversationaisetting.model.aggregate.ChatConversationAiSetting;
import com.mindconnect.domain.chatconversationaisetting.model.valueobject.ChatConversationAiSettingId;
import com.mindconnect.domain.chatconversationaisetting.port.repository.ChatConversationAiSettingRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ChatConversationAiSettingUseCasesTest {

    private FakeChatConversationAiSettingRepository repository;
    private RegisterChatConversationAiSettingUseCase register;
    private GetChatConversationAiSettingByIdUseCase getById;
    private ListChatConversationAiSettingUseCase list;
    private UpdateChatConversationAiSettingUseCase update;

    @BeforeEach
    void setUp() {
        repository = new FakeChatConversationAiSettingRepository();
        register = new RegisterChatConversationAiSettingUseCase(repository);
        getById = new GetChatConversationAiSettingByIdUseCase(repository);
        list = new ListChatConversationAiSettingUseCase(repository);
        update = new UpdateChatConversationAiSettingUseCase(repository);
    }

    @Test
    void registrarGuardaYDevuelveElRegistroConIdGenerado() {
        ChatConversationAiSettingResponse created = register.execute(commandA());

        assertNotNull(created.id());
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void buscarPorIdDevuelveLoGuardado() {
        ChatConversationAiSettingResponse created = register.execute(commandA());

        assertEquals(created, getById.execute(new ChatConversationAiSettingId(created.id())));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(ChatConversationAiSettingNotFoundApplicationException.class, () -> getById.execute(ChatConversationAiSettingId.generate()));
    }

    @Test
    void listarDevuelveTodosLosRegistros() {
        register.execute(commandA());
        register.execute(commandA());

        assertEquals(2, list.execute().size());
    }

    @Test
    void actualizarConservaElId() {
        ChatConversationAiSettingResponse created = register.execute(commandA());

        ChatConversationAiSettingResponse updated = update.execute(updateCommand(new ChatConversationAiSettingId(created.id())));

        assertEquals(created.id(), updated.id());
        assertEquals(updated, getById.execute(new ChatConversationAiSettingId(created.id())));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(ChatConversationAiSettingNotFoundApplicationException.class, () -> update.execute(updateCommand(ChatConversationAiSettingId.generate())));
    }

    private RegisterChatConversationAiSettingCommand commandA() {
        return new RegisterChatConversationAiSettingCommand(UUID.randomUUID(), true, UUID.randomUUID());
    }

    private UpdateChatConversationAiSettingCommand updateCommand(ChatConversationAiSettingId id) {
        return new UpdateChatConversationAiSettingCommand(id, UUID.randomUUID(), false, UUID.randomUUID());
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeChatConversationAiSettingRepository implements ChatConversationAiSettingRepository {
        private final Map<ChatConversationAiSettingId, ChatConversationAiSetting> store = new LinkedHashMap<>();

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
            store.remove(aggregate.id());
        }
    }
}
