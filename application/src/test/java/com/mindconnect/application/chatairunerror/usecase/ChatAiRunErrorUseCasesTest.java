package com.mindconnect.application.chatairunerror.usecase;

import java.util.UUID;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.chatairunerror.command.RegisterChatAiRunErrorCommand;
import com.mindconnect.application.chatairunerror.command.UpdateChatAiRunErrorCommand;
import com.mindconnect.application.chatairunerror.dto.ChatAiRunErrorResponse;
import com.mindconnect.application.chatairunerror.exception.ChatAiRunErrorNotFoundApplicationException;
import com.mindconnect.domain.chatairunerror.model.aggregate.ChatAiRunError;
import com.mindconnect.domain.chatairunerror.model.valueobject.ChatAiRunErrorId;
import com.mindconnect.domain.chatairunerror.port.repository.ChatAiRunErrorRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ChatAiRunErrorUseCasesTest {

    private FakeChatAiRunErrorRepository repository;
    private RegisterChatAiRunErrorUseCase register;
    private GetChatAiRunErrorByIdUseCase getById;
    private ListChatAiRunErrorUseCase list;
    private UpdateChatAiRunErrorUseCase update;

    @BeforeEach
    void setUp() {
        repository = new FakeChatAiRunErrorRepository();
        register = new RegisterChatAiRunErrorUseCase(repository);
        getById = new GetChatAiRunErrorByIdUseCase(repository);
        list = new ListChatAiRunErrorUseCase(repository);
        update = new UpdateChatAiRunErrorUseCase(repository);
    }

    @Test
    void registrarGuardaYDevuelveElRegistroConIdGenerado() {
        ChatAiRunErrorResponse created = register.execute(commandA());

        assertNotNull(created.id());
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void buscarPorIdDevuelveLoGuardado() {
        ChatAiRunErrorResponse created = register.execute(commandA());

        assertEquals(created, getById.execute(new ChatAiRunErrorId(created.id())));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(ChatAiRunErrorNotFoundApplicationException.class, () -> getById.execute(ChatAiRunErrorId.generate()));
    }

    @Test
    void listarDevuelveTodosLosRegistros() {
        register.execute(commandA());
        register.execute(commandA());

        assertEquals(2, list.execute().size());
    }

    @Test
    void actualizarConservaElId() {
        ChatAiRunErrorResponse created = register.execute(commandA());

        ChatAiRunErrorResponse updated = update.execute(updateCommand(new ChatAiRunErrorId(created.id())));

        assertEquals(created.id(), updated.id());
        assertEquals(updated, getById.execute(new ChatAiRunErrorId(created.id())));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(ChatAiRunErrorNotFoundApplicationException.class, () -> update.execute(updateCommand(ChatAiRunErrorId.generate())));
    }

    private RegisterChatAiRunErrorCommand commandA() {
        return new RegisterChatAiRunErrorCommand(UUID.randomUUID(), "valor-a", "valor-a", "valor-a");
    }

    private UpdateChatAiRunErrorCommand updateCommand(ChatAiRunErrorId id) {
        return new UpdateChatAiRunErrorCommand(id, UUID.randomUUID(), "valor-b", "valor-b", "valor-b");
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeChatAiRunErrorRepository implements ChatAiRunErrorRepository {
        private final Map<ChatAiRunErrorId, ChatAiRunError> store = new LinkedHashMap<>();

        @Override
        public ChatAiRunError save(ChatAiRunError aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<ChatAiRunError> findById(ChatAiRunErrorId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<ChatAiRunError> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(ChatAiRunError aggregate) {
            store.remove(aggregate.id());
        }
    }
}
