package com.mindconnect.application.chatairunmetric.usecase;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.chatairunmetric.command.RegisterChatAiRunMetricCommand;
import com.mindconnect.application.chatairunmetric.command.UpdateChatAiRunMetricCommand;
import com.mindconnect.application.chatairunmetric.dto.ChatAiRunMetricResponse;
import com.mindconnect.application.chatairunmetric.exception.ChatAiRunMetricNotFoundApplicationException;
import com.mindconnect.domain.chatairunmetric.model.aggregate.ChatAiRunMetric;
import com.mindconnect.domain.chatairunmetric.model.valueobject.ChatAiRunMetricId;
import com.mindconnect.domain.chatairunmetric.port.repository.ChatAiRunMetricRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ChatAiRunMetricUseCasesTest {

    private FakeChatAiRunMetricRepository repository;
    private RegisterChatAiRunMetricUseCase register;
    private GetChatAiRunMetricByIdUseCase getById;
    private ListChatAiRunMetricUseCase list;
    private UpdateChatAiRunMetricUseCase update;

    @BeforeEach
    void setUp() {
        repository = new FakeChatAiRunMetricRepository();
        register = new RegisterChatAiRunMetricUseCase(repository);
        getById = new GetChatAiRunMetricByIdUseCase(repository);
        list = new ListChatAiRunMetricUseCase(repository);
        update = new UpdateChatAiRunMetricUseCase(repository);
    }

    @Test
    void registrarGuardaYDevuelveElRegistroConIdGenerado() {
        ChatAiRunMetricResponse created = register.execute(commandA());

        assertNotNull(created.id());
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void buscarPorIdDevuelveLoGuardado() {
        ChatAiRunMetricResponse created = register.execute(commandA());

        assertEquals(created, getById.execute(new ChatAiRunMetricId(created.id())));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(ChatAiRunMetricNotFoundApplicationException.class, () -> getById.execute(ChatAiRunMetricId.generate()));
    }

    @Test
    void listarDevuelveTodosLosRegistros() {
        register.execute(commandA());
        register.execute(commandA());

        assertEquals(2, list.execute().size());
    }

    @Test
    void actualizarConservaElId() {
        ChatAiRunMetricResponse created = register.execute(commandA());

        ChatAiRunMetricResponse updated = update.execute(updateCommand(new ChatAiRunMetricId(created.id())));

        assertEquals(created.id(), updated.id());
        assertEquals(updated, getById.execute(new ChatAiRunMetricId(created.id())));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(ChatAiRunMetricNotFoundApplicationException.class, () -> update.execute(updateCommand(ChatAiRunMetricId.generate())));
    }

    private RegisterChatAiRunMetricCommand commandA() {
        return new RegisterChatAiRunMetricCommand(UUID.randomUUID(), 1, 1, 1, BigDecimal.ONE);
    }

    private UpdateChatAiRunMetricCommand updateCommand(ChatAiRunMetricId id) {
        return new UpdateChatAiRunMetricCommand(id, UUID.randomUUID(), 2, 2, 2, BigDecimal.TEN);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeChatAiRunMetricRepository implements ChatAiRunMetricRepository {
        private final Map<ChatAiRunMetricId, ChatAiRunMetric> store = new LinkedHashMap<>();

        @Override
        public ChatAiRunMetric save(ChatAiRunMetric aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<ChatAiRunMetric> findById(ChatAiRunMetricId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<ChatAiRunMetric> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(ChatAiRunMetric aggregate) {
            store.remove(aggregate.id());
        }
    }
}
