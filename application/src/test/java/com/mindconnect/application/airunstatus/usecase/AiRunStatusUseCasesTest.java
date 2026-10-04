package com.mindconnect.application.airunstatus.usecase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.airunstatus.command.RegisterAiRunStatusCommand;
import com.mindconnect.application.airunstatus.command.UpdateAiRunStatusCommand;
import com.mindconnect.application.airunstatus.dto.AiRunStatusResponse;
import com.mindconnect.application.airunstatus.exception.AiRunStatusNotFoundApplicationException;
import com.mindconnect.domain.airunstatus.model.aggregate.AiRunStatus;
import com.mindconnect.domain.airunstatus.model.valueobject.AiRunStatusId;
import com.mindconnect.domain.airunstatus.port.repository.AiRunStatusRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AiRunStatusUseCasesTest {

    private FakeAiRunStatusRepository repository;
    private RegisterAiRunStatusUseCase register;
    private GetAiRunStatusByIdUseCase getById;
    private ListAiRunStatusUseCase list;
    private UpdateAiRunStatusUseCase update;

    @BeforeEach
    void setUp() {
        repository = new FakeAiRunStatusRepository();
        register = new RegisterAiRunStatusUseCase(repository);
        getById = new GetAiRunStatusByIdUseCase(repository);
        list = new ListAiRunStatusUseCase(repository);
        update = new UpdateAiRunStatusUseCase(repository);
    }

    @Test
    void registrarGuardaYDevuelveElRegistroConIdGenerado() {
        AiRunStatusResponse created = register.execute(commandA());

        assertNotNull(created.id());
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void buscarPorIdDevuelveLoGuardado() {
        AiRunStatusResponse created = register.execute(commandA());

        assertEquals(created, getById.execute(new AiRunStatusId(created.id())));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(AiRunStatusNotFoundApplicationException.class, () -> getById.execute(AiRunStatusId.generate()));
    }

    @Test
    void listarDevuelveTodosLosRegistros() {
        register.execute(commandA());
        register.execute(commandA());

        assertEquals(2, list.execute().size());
    }

    @Test
    void actualizarConservaElId() {
        AiRunStatusResponse created = register.execute(commandA());

        AiRunStatusResponse updated = update.execute(updateCommand(new AiRunStatusId(created.id())));

        assertEquals(created.id(), updated.id());
        assertEquals(updated, getById.execute(new AiRunStatusId(created.id())));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(AiRunStatusNotFoundApplicationException.class, () -> update.execute(updateCommand(AiRunStatusId.generate())));
    }

    private RegisterAiRunStatusCommand commandA() {
        return new RegisterAiRunStatusCommand("valor-a");
    }

    private UpdateAiRunStatusCommand updateCommand(AiRunStatusId id) {
        return new UpdateAiRunStatusCommand(id, "valor-b");
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeAiRunStatusRepository implements AiRunStatusRepository {
        private final Map<AiRunStatusId, AiRunStatus> store = new LinkedHashMap<>();

        @Override
        public AiRunStatus save(AiRunStatus aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<AiRunStatus> findById(AiRunStatusId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<AiRunStatus> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(AiRunStatus aggregate) {
            store.remove(aggregate.id());
        }
    }
}
