package com.mindconnect.application.chatconversation.usecase;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.chatconversation.command.RegisterChatConversationCommand;
import com.mindconnect.application.chatconversation.command.UpdateChatConversationCommand;
import com.mindconnect.application.chatconversation.dto.ChatConversationResponse;
import com.mindconnect.application.chatconversation.exception.ChatConversationNotFoundApplicationException;
import com.mindconnect.domain.chatconversation.model.aggregate.ChatConversation;
import com.mindconnect.domain.chatconversation.model.valueobject.ChatConversationId;
import com.mindconnect.domain.chatconversation.port.repository.ChatConversationRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ChatConversationUseCasesTest {

    private FakeChatConversationRepository repository;
    private RegisterChatConversationUseCase register;
    private GetChatConversationByIdUseCase getById;
    private ListChatConversationUseCase list;
    private UpdateChatConversationUseCase update;

    @BeforeEach
    void setUp() {
        repository = new FakeChatConversationRepository();
        register = new RegisterChatConversationUseCase(repository);
        getById = new GetChatConversationByIdUseCase(repository);
        list = new ListChatConversationUseCase(repository);
        update = new UpdateChatConversationUseCase(repository);
    }

    @Test
    void registrarGuardaYDevuelveElRegistroConIdGenerado() {
        ChatConversationResponse created = register.execute(commandA());

        assertNotNull(created.id());
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void buscarPorIdDevuelveLoGuardado() {
        ChatConversationResponse created = register.execute(commandA());

        assertEquals(created, getById.execute(new ChatConversationId(created.id())));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(ChatConversationNotFoundApplicationException.class, () -> getById.execute(ChatConversationId.generate()));
    }

    @Test
    void listarDevuelveTodosLosRegistros() {
        register.execute(commandA());
        register.execute(commandA());

        assertEquals(2, list.execute().size());
    }

    @Test
    void actualizarConservaElId() {
        ChatConversationResponse created = register.execute(commandA());

        ChatConversationResponse updated = update.execute(updateCommand(new ChatConversationId(created.id())));

        assertEquals(created.id(), updated.id());
        assertEquals(updated, getById.execute(new ChatConversationId(created.id())));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(ChatConversationNotFoundApplicationException.class, () -> update.execute(updateCommand(ChatConversationId.generate())));
    }

    private RegisterChatConversationCommand commandA() {
        return new RegisterChatConversationCommand(UUID.randomUUID(), UUID.randomUUID(), LocalDateTime.now(), true, LocalDateTime.now(), UUID.randomUUID());
    }

    private UpdateChatConversationCommand updateCommand(ChatConversationId id) {
        return new UpdateChatConversationCommand(id, UUID.randomUUID(), UUID.randomUUID(), LocalDateTime.now(), false, LocalDateTime.now(), UUID.randomUUID());
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeChatConversationRepository implements ChatConversationRepository {
        private final Map<ChatConversationId, ChatConversation> store = new LinkedHashMap<>();

        @Override
        public ChatConversation save(ChatConversation aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<ChatConversation> findById(ChatConversationId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<ChatConversation> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(ChatConversation aggregate) {
            store.remove(aggregate.id());
        }
    }
}
