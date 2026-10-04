package com.mindconnect.application.stateregion.usecase;

import java.util.UUID;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.stateregion.command.RegisterStateRegionCommand;
import com.mindconnect.application.stateregion.command.UpdateStateRegionCommand;
import com.mindconnect.application.stateregion.dto.StateRegionResponse;
import com.mindconnect.application.stateregion.exception.StateRegionNotFoundApplicationException;
import com.mindconnect.domain.stateregion.model.aggregate.StateRegion;
import com.mindconnect.domain.stateregion.model.valueobject.StateRegionId;
import com.mindconnect.domain.stateregion.port.repository.StateRegionRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StateRegionUseCasesTest {

    private FakeStateRegionRepository repository;
    private RegisterStateRegionUseCase register;
    private GetStateRegionByIdUseCase getById;
    private ListStateRegionUseCase list;
    private UpdateStateRegionUseCase update;

    @BeforeEach
    void setUp() {
        repository = new FakeStateRegionRepository();
        register = new RegisterStateRegionUseCase(repository);
        getById = new GetStateRegionByIdUseCase(repository);
        list = new ListStateRegionUseCase(repository);
        update = new UpdateStateRegionUseCase(repository);
    }

    @Test
    void registrarGuardaYDevuelveElRegistroConIdGenerado() {
        StateRegionResponse created = register.execute(commandA());

        assertNotNull(created.id());
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void buscarPorIdDevuelveLoGuardado() {
        StateRegionResponse created = register.execute(commandA());

        assertEquals(created, getById.execute(new StateRegionId(created.id())));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(StateRegionNotFoundApplicationException.class, () -> getById.execute(StateRegionId.generate()));
    }

    @Test
    void listarDevuelveTodosLosRegistros() {
        register.execute(commandA());
        register.execute(commandA());

        assertEquals(2, list.execute().size());
    }

    @Test
    void actualizarConservaElId() {
        StateRegionResponse created = register.execute(commandA());

        StateRegionResponse updated = update.execute(updateCommand(new StateRegionId(created.id())));

        assertEquals(created.id(), updated.id());
        assertEquals(updated, getById.execute(new StateRegionId(created.id())));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(StateRegionNotFoundApplicationException.class, () -> update.execute(updateCommand(StateRegionId.generate())));
    }

    private RegisterStateRegionCommand commandA() {
        return new RegisterStateRegionCommand("valor-a", "valor-a", "valor-a", UUID.randomUUID());
    }

    private UpdateStateRegionCommand updateCommand(StateRegionId id) {
        return new UpdateStateRegionCommand(id, "valor-b", "valor-b", "valor-b", UUID.randomUUID());
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeStateRegionRepository implements StateRegionRepository {
        private final Map<StateRegionId, StateRegion> store = new LinkedHashMap<>();

        @Override
        public StateRegion save(StateRegion aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<StateRegion> findById(StateRegionId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<StateRegion> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(StateRegion aggregate) {
            store.remove(aggregate.id());
        }
    }
}
