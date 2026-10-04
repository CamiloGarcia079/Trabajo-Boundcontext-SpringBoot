package com.mindconnect.application.encounterstatus.usecase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.encounterstatus.command.RegisterEncounterStatusCommand;
import com.mindconnect.application.encounterstatus.command.UpdateEncounterStatusCommand;
import com.mindconnect.application.encounterstatus.dto.EncounterStatusResponse;
import com.mindconnect.application.encounterstatus.exception.EncounterStatusNotFoundApplicationException;
import com.mindconnect.domain.encounterstatus.model.aggregate.EncounterStatus;
import com.mindconnect.domain.encounterstatus.model.valueobject.EncounterStatusId;
import com.mindconnect.domain.encounterstatus.port.repository.EncounterStatusRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EncounterStatusUseCasesTest {

    private FakeEncounterStatusRepository repository;
    private RegisterEncounterStatusUseCase register;
    private GetEncounterStatusByIdUseCase getById;
    private ListEncounterStatusUseCase list;
    private UpdateEncounterStatusUseCase update;

    @BeforeEach
    void setUp() {
        repository = new FakeEncounterStatusRepository();
        register = new RegisterEncounterStatusUseCase(repository);
        getById = new GetEncounterStatusByIdUseCase(repository);
        list = new ListEncounterStatusUseCase(repository);
        update = new UpdateEncounterStatusUseCase(repository);
    }

    @Test
    void registrarGuardaYDevuelveElRegistroConIdGenerado() {
        EncounterStatusResponse created = register.execute(commandA());

        assertNotNull(created.id());
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void buscarPorIdDevuelveLoGuardado() {
        EncounterStatusResponse created = register.execute(commandA());

        assertEquals(created, getById.execute(new EncounterStatusId(created.id())));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(EncounterStatusNotFoundApplicationException.class, () -> getById.execute(EncounterStatusId.generate()));
    }

    @Test
    void listarDevuelveTodosLosRegistros() {
        register.execute(commandA());
        register.execute(commandA());

        assertEquals(2, list.execute().size());
    }

    @Test
    void actualizarConservaElId() {
        EncounterStatusResponse created = register.execute(commandA());

        EncounterStatusResponse updated = update.execute(updateCommand(new EncounterStatusId(created.id())));

        assertEquals(created.id(), updated.id());
        assertEquals(updated, getById.execute(new EncounterStatusId(created.id())));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(EncounterStatusNotFoundApplicationException.class, () -> update.execute(updateCommand(EncounterStatusId.generate())));
    }

    private RegisterEncounterStatusCommand commandA() {
        return new RegisterEncounterStatusCommand("valor-a", "valor-a");
    }

    private UpdateEncounterStatusCommand updateCommand(EncounterStatusId id) {
        return new UpdateEncounterStatusCommand(id, "valor-b", "valor-b");
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeEncounterStatusRepository implements EncounterStatusRepository {
        private final Map<EncounterStatusId, EncounterStatus> store = new LinkedHashMap<>();

        @Override
        public EncounterStatus save(EncounterStatus aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<EncounterStatus> findById(EncounterStatusId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<EncounterStatus> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(EncounterStatus aggregate) {
            store.remove(aggregate.id());
        }
    }
}
