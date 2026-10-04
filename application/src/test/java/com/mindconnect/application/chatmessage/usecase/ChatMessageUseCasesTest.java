package com.mindconnect.application.chatmessage.usecase;

import java.util.UUID;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.chatmessage.command.RegisterChatMessageCommand;
import com.mindconnect.application.chatmessage.command.UpdateChatMessageCommand;
import com.mindconnect.application.chatmessage.dto.ChatMessageResponse;
import com.mindconnect.application.chatmessage.exception.ChatMessageNotFoundApplicationException;
import com.mindconnect.domain.chatmessage.model.aggregate.ChatMessage;
import com.mindconnect.domain.chatmessage.model.valueobject.ChatMessageId;
import com.mindconnect.domain.chatmessage.port.repository.ChatMessageRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ChatMessageUseCasesTest {

    private FakeChatMessageRepository repository;
    private RegisterChatMessageUseCase register;
    private GetChatMessageByIdUseCase getById;
    private ListChatMessageUseCase list;
    private UpdateChatMessageUseCase update;

    @BeforeEach
    void setUp() {
        repository = new FakeChatMessageRepository();
        register = new RegisterChatMessageUseCase(repository);
        getById = new GetChatMessageByIdUseCase(repository);
        list = new ListChatMessageUseCase(repository);
        update = new UpdateChatMessageUseCase(repository);
    }

    @Test
    void registrarGuardaYDevuelveElRegistroConIdGenerado() {
        ChatMessageResponse created = register.execute(commandA());

        assertNotNull(created.id());
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void buscarPorIdDevuelveLoGuardado() {
        ChatMessageResponse created = register.execute(commandA());

        assertEquals(created, getById.execute(new ChatMessageId(created.id())));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(ChatMessageNotFoundApplicationException.class, () -> getById.execute(ChatMessageId.generate()));
    }

    @Test
    void listarDevuelveTodosLosRegistros() {
        register.execute(commandA());
        register.execute(commandA());

        assertEquals(2, list.execute().size());
    }

    @Test
    void actualizarConservaElId() {
        ChatMessageResponse created = register.execute(commandA());

        ChatMessageResponse updated = update.execute(updateCommand(new ChatMessageId(created.id())));

        assertEquals(created.id(), updated.id());
        assertEquals(updated, getById.execute(new ChatMessageId(created.id())));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(ChatMessageNotFoundApplicationException.class, () -> update.execute(updateCommand(ChatMessageId.generate())));
    }

    private RegisterChatMessageCommand commandA() {
        return new RegisterChatMessageCommand(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), "valor-a", "valor-a");
    }

    private UpdateChatMessageCommand updateCommand(ChatMessageId id) {
        return new UpdateChatMessageCommand(id, UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), "valor-b", "valor-b");
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeChatMessageRepository implements ChatMessageRepository {
        private final Map<ChatMessageId, ChatMessage> store = new LinkedHashMap<>();

        @Override
        public ChatMessage save(ChatMessage aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<ChatMessage> findById(ChatMessageId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<ChatMessage> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(ChatMessage aggregate) {
            store.remove(aggregate.id());
        }
    }
}
