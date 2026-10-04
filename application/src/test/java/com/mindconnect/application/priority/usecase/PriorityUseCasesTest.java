package com.mindconnect.application.priority.usecase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.priority.command.RegisterPriorityCommand;
import com.mindconnect.application.priority.command.UpdatePriorityCommand;
import com.mindconnect.application.priority.dto.PriorityResponse;
import com.mindconnect.application.priority.exception.PriorityNotFoundApplicationException;
import com.mindconnect.domain.priority.model.aggregate.Priority;
import com.mindconnect.domain.priority.model.valueobject.PriorityId;
import com.mindconnect.domain.priority.port.repository.PriorityRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PriorityUseCasesTest {

    private FakePriorityRepository repository;
    private RegisterPriorityUseCase register;
    private GetPriorityByIdUseCase getById;
    private ListPriorityUseCase list;
    private UpdatePriorityUseCase update;

    @BeforeEach
    void setUp() {
        repository = new FakePriorityRepository();
        register = new RegisterPriorityUseCase(repository);
        getById = new GetPriorityByIdUseCase(repository);
        list = new ListPriorityUseCase(repository);
        update = new UpdatePriorityUseCase(repository);
    }

    @Test
    void registrarGuardaYDevuelveElRegistroConIdGenerado() {
        PriorityResponse created = register.execute(commandA());

        assertNotNull(created.id());
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void buscarPorIdDevuelveLoGuardado() {
        PriorityResponse created = register.execute(commandA());

        assertEquals(created, getById.execute(new PriorityId(created.id())));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(PriorityNotFoundApplicationException.class, () -> getById.execute(PriorityId.generate()));
    }

    @Test
    void listarDevuelveTodosLosRegistros() {
        register.execute(commandA());
        register.execute(commandA());

        assertEquals(2, list.execute().size());
    }

    @Test
    void actualizarConservaElId() {
        PriorityResponse created = register.execute(commandA());

        PriorityResponse updated = update.execute(updateCommand(new PriorityId(created.id())));

        assertEquals(created.id(), updated.id());
        assertEquals(updated, getById.execute(new PriorityId(created.id())));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(PriorityNotFoundApplicationException.class, () -> update.execute(updateCommand(PriorityId.generate())));
    }

    private RegisterPriorityCommand commandA() {
        return new RegisterPriorityCommand("valor-a");
    }

    private UpdatePriorityCommand updateCommand(PriorityId id) {
        return new UpdatePriorityCommand(id, "valor-b");
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakePriorityRepository implements PriorityRepository {
        private final Map<PriorityId, Priority> store = new LinkedHashMap<>();

        @Override
        public Priority save(Priority aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<Priority> findById(PriorityId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<Priority> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(Priority aggregate) {
            store.remove(aggregate.id());
        }
    }
}
