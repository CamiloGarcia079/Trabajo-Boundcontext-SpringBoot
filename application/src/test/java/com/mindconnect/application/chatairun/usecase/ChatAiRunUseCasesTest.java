package com.mindconnect.application.chatairun.usecase;

import java.util.UUID;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.chatairun.command.RegisterChatAiRunCommand;
import com.mindconnect.application.chatairun.command.UpdateChatAiRunCommand;
import com.mindconnect.application.chatairun.dto.ChatAiRunResponse;
import com.mindconnect.application.chatairun.exception.ChatAiRunNotFoundApplicationException;
import com.mindconnect.domain.chatairun.model.aggregate.ChatAiRun;
import com.mindconnect.domain.chatairun.model.valueobject.ChatAiRunId;
import com.mindconnect.domain.chatairun.port.repository.ChatAiRunRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ChatAiRunUseCasesTest {

    private FakeChatAiRunRepository repository;
    private RegisterChatAiRunUseCase register;
    private GetChatAiRunByIdUseCase getById;
    private ListChatAiRunUseCase list;
    private UpdateChatAiRunUseCase update;

    @BeforeEach
    void setUp() {
        repository = new FakeChatAiRunRepository();
        register = new RegisterChatAiRunUseCase(repository);
        getById = new GetChatAiRunByIdUseCase(repository);
        list = new ListChatAiRunUseCase(repository);
        update = new UpdateChatAiRunUseCase(repository);
    }

    @Test
    void registrarGuardaYDevuelveElRegistroConIdGenerado() {
        ChatAiRunResponse created = register.execute(commandA());

        assertNotNull(created.id());
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void buscarPorIdDevuelveLoGuardado() {
        ChatAiRunResponse created = register.execute(commandA());

        assertEquals(created, getById.execute(new ChatAiRunId(created.id())));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(ChatAiRunNotFoundApplicationException.class, () -> getById.execute(ChatAiRunId.generate()));
    }

    @Test
    void listarDevuelveTodosLosRegistros() {
        register.execute(commandA());
        register.execute(commandA());

        assertEquals(2, list.execute().size());
    }

    @Test
    void actualizarConservaElId() {
        ChatAiRunResponse created = register.execute(commandA());

        ChatAiRunResponse updated = update.execute(updateCommand(new ChatAiRunId(created.id())));

        assertEquals(created.id(), updated.id());
        assertEquals(updated, getById.execute(new ChatAiRunId(created.id())));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(ChatAiRunNotFoundApplicationException.class, () -> update.execute(updateCommand(ChatAiRunId.generate())));
    }

    private RegisterChatAiRunCommand commandA() {
        return new RegisterChatAiRunCommand(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());
    }

    private UpdateChatAiRunCommand updateCommand(ChatAiRunId id) {
        return new UpdateChatAiRunCommand(id, UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeChatAiRunRepository implements ChatAiRunRepository {
        private final Map<ChatAiRunId, ChatAiRun> store = new LinkedHashMap<>();

        @Override
        public ChatAiRun save(ChatAiRun aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<ChatAiRun> findById(ChatAiRunId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<ChatAiRun> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(ChatAiRun aggregate) {
            store.remove(aggregate.id());
        }
    }
}
