package com.mindconnect.application.chatescalationstatushistory.usecase;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.chatescalationstatushistory.command.RegisterChatEscalationStatusHistoryCommand;
import com.mindconnect.application.chatescalationstatushistory.command.UpdateChatEscalationStatusHistoryCommand;
import com.mindconnect.application.chatescalationstatushistory.dto.ChatEscalationStatusHistoryResponse;
import com.mindconnect.application.chatescalationstatushistory.exception.ChatEscalationStatusHistoryNotFoundApplicationException;
import com.mindconnect.domain.chatescalationstatushistory.model.aggregate.ChatEscalationStatusHistory;
import com.mindconnect.domain.chatescalationstatushistory.model.valueobject.ChatEscalationStatusHistoryId;
import com.mindconnect.domain.chatescalationstatushistory.port.repository.ChatEscalationStatusHistoryRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ChatEscalationStatusHistoryUseCasesTest {

    private FakeChatEscalationStatusHistoryRepository repository;
    private RegisterChatEscalationStatusHistoryUseCase register;
    private GetChatEscalationStatusHistoryByIdUseCase getById;
    private ListChatEscalationStatusHistoryUseCase list;
    private UpdateChatEscalationStatusHistoryUseCase update;

    @BeforeEach
    void setUp() {
        repository = new FakeChatEscalationStatusHistoryRepository();
        register = new RegisterChatEscalationStatusHistoryUseCase(repository);
        getById = new GetChatEscalationStatusHistoryByIdUseCase(repository);
        list = new ListChatEscalationStatusHistoryUseCase(repository);
        update = new UpdateChatEscalationStatusHistoryUseCase(repository);
    }

    @Test
    void registrarGuardaYDevuelveElRegistroConIdGenerado() {
        ChatEscalationStatusHistoryResponse created = register.execute(commandA());

        assertNotNull(created.id());
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void buscarPorIdDevuelveLoGuardado() {
        ChatEscalationStatusHistoryResponse created = register.execute(commandA());

        assertEquals(created, getById.execute(new ChatEscalationStatusHistoryId(created.id())));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(ChatEscalationStatusHistoryNotFoundApplicationException.class, () -> getById.execute(ChatEscalationStatusHistoryId.generate()));
    }

    @Test
    void listarDevuelveTodosLosRegistros() {
        register.execute(commandA());
        register.execute(commandA());

        assertEquals(2, list.execute().size());
    }

    @Test
    void actualizarConservaElId() {
        ChatEscalationStatusHistoryResponse created = register.execute(commandA());

        ChatEscalationStatusHistoryResponse updated = update.execute(updateCommand(new ChatEscalationStatusHistoryId(created.id())));

        assertEquals(created.id(), updated.id());
        assertEquals(updated, getById.execute(new ChatEscalationStatusHistoryId(created.id())));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(ChatEscalationStatusHistoryNotFoundApplicationException.class, () -> update.execute(updateCommand(ChatEscalationStatusHistoryId.generate())));
    }

    private RegisterChatEscalationStatusHistoryCommand commandA() {
        return new RegisterChatEscalationStatusHistoryCommand(UUID.randomUUID(), UUID.randomUUID(), LocalDateTime.now());
    }

    private UpdateChatEscalationStatusHistoryCommand updateCommand(ChatEscalationStatusHistoryId id) {
        return new UpdateChatEscalationStatusHistoryCommand(id, UUID.randomUUID(), UUID.randomUUID(), LocalDateTime.now());
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeChatEscalationStatusHistoryRepository implements ChatEscalationStatusHistoryRepository {
        private final Map<ChatEscalationStatusHistoryId, ChatEscalationStatusHistory> store = new LinkedHashMap<>();

        @Override
        public ChatEscalationStatusHistory save(ChatEscalationStatusHistory aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<ChatEscalationStatusHistory> findById(ChatEscalationStatusHistoryId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<ChatEscalationStatusHistory> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(ChatEscalationStatusHistory aggregate) {
            store.remove(aggregate.id());
        }
    }
}
