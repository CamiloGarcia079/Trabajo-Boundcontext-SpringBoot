package com.mindconnect.application.conversationstatus.usecase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.conversationstatus.command.RegisterConversationStatusCommand;
import com.mindconnect.application.conversationstatus.command.UpdateConversationStatusCommand;
import com.mindconnect.application.conversationstatus.dto.ConversationStatusResponse;
import com.mindconnect.application.conversationstatus.exception.ConversationStatusNotFoundApplicationException;
import com.mindconnect.domain.conversationstatus.model.aggregate.ConversationStatus;
import com.mindconnect.domain.conversationstatus.model.valueobject.ConversationStatusId;
import com.mindconnect.domain.conversationstatus.port.repository.ConversationStatusRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConversationStatusUseCasesTest {

    private FakeConversationStatusRepository repository;
    private RegisterConversationStatusUseCase register;
    private GetConversationStatusByIdUseCase getById;
    private ListConversationStatusUseCase list;
    private UpdateConversationStatusUseCase update;

    @BeforeEach
    void setUp() {
        repository = new FakeConversationStatusRepository();
        register = new RegisterConversationStatusUseCase(repository);
        getById = new GetConversationStatusByIdUseCase(repository);
        list = new ListConversationStatusUseCase(repository);
        update = new UpdateConversationStatusUseCase(repository);
    }

    @Test
    void registrarGuardaYDevuelveElRegistroConIdGenerado() {
        ConversationStatusResponse created = register.execute(commandA());

        assertNotNull(created.id());
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void buscarPorIdDevuelveLoGuardado() {
        ConversationStatusResponse created = register.execute(commandA());

        assertEquals(created, getById.execute(new ConversationStatusId(created.id())));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(ConversationStatusNotFoundApplicationException.class, () -> getById.execute(ConversationStatusId.generate()));
    }

    @Test
    void listarDevuelveTodosLosRegistros() {
        register.execute(commandA());
        register.execute(commandA());

        assertEquals(2, list.execute().size());
    }

    @Test
    void actualizarConservaElId() {
        ConversationStatusResponse created = register.execute(commandA());

        ConversationStatusResponse updated = update.execute(updateCommand(new ConversationStatusId(created.id())));

        assertEquals(created.id(), updated.id());
        assertEquals(updated, getById.execute(new ConversationStatusId(created.id())));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(ConversationStatusNotFoundApplicationException.class, () -> update.execute(updateCommand(ConversationStatusId.generate())));
    }

    private RegisterConversationStatusCommand commandA() {
        return new RegisterConversationStatusCommand("valor-a");
    }

    private UpdateConversationStatusCommand updateCommand(ConversationStatusId id) {
        return new UpdateConversationStatusCommand(id, "valor-b");
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeConversationStatusRepository implements ConversationStatusRepository {
        private final Map<ConversationStatusId, ConversationStatus> store = new LinkedHashMap<>();

        @Override
        public ConversationStatus save(ConversationStatus aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<ConversationStatus> findById(ConversationStatusId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<ConversationStatus> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(ConversationStatus aggregate) {
            store.remove(aggregate.id());
        }
    }
}
