package com.mindconnect.application.encountertype.usecase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.encountertype.command.RegisterEncounterTypeCommand;
import com.mindconnect.application.encountertype.command.UpdateEncounterTypeCommand;
import com.mindconnect.application.encountertype.dto.EncounterTypeResponse;
import com.mindconnect.application.encountertype.exception.EncounterTypeNotFoundApplicationException;
import com.mindconnect.domain.encountertype.model.aggregate.EncounterType;
import com.mindconnect.domain.encountertype.model.valueobject.EncounterTypeId;
import com.mindconnect.domain.encountertype.port.repository.EncounterTypeRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EncounterTypeUseCasesTest {

    private FakeEncounterTypeRepository repository;
    private RegisterEncounterTypeUseCase register;
    private GetEncounterTypeByIdUseCase getById;
    private ListEncounterTypeUseCase list;
    private UpdateEncounterTypeUseCase update;

    @BeforeEach
    void setUp() {
        repository = new FakeEncounterTypeRepository();
        register = new RegisterEncounterTypeUseCase(repository);
        getById = new GetEncounterTypeByIdUseCase(repository);
        list = new ListEncounterTypeUseCase(repository);
        update = new UpdateEncounterTypeUseCase(repository);
    }

    @Test
    void registrarGuardaYDevuelveElRegistroConIdGenerado() {
        EncounterTypeResponse created = register.execute(commandA());

        assertNotNull(created.id());
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void buscarPorIdDevuelveLoGuardado() {
        EncounterTypeResponse created = register.execute(commandA());

        assertEquals(created, getById.execute(new EncounterTypeId(created.id())));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(EncounterTypeNotFoundApplicationException.class, () -> getById.execute(EncounterTypeId.generate()));
    }

    @Test
    void listarDevuelveTodosLosRegistros() {
        register.execute(commandA());
        register.execute(commandA());

        assertEquals(2, list.execute().size());
    }

    @Test
    void actualizarConservaElId() {
        EncounterTypeResponse created = register.execute(commandA());

        EncounterTypeResponse updated = update.execute(updateCommand(new EncounterTypeId(created.id())));

        assertEquals(created.id(), updated.id());
        assertEquals(updated, getById.execute(new EncounterTypeId(created.id())));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(EncounterTypeNotFoundApplicationException.class, () -> update.execute(updateCommand(EncounterTypeId.generate())));
    }

    private RegisterEncounterTypeCommand commandA() {
        return new RegisterEncounterTypeCommand("valor-a", "valor-a");
    }

    private UpdateEncounterTypeCommand updateCommand(EncounterTypeId id) {
        return new UpdateEncounterTypeCommand(id, "valor-b", "valor-b");
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeEncounterTypeRepository implements EncounterTypeRepository {
        private final Map<EncounterTypeId, EncounterType> store = new LinkedHashMap<>();

        @Override
        public EncounterType save(EncounterType aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<EncounterType> findById(EncounterTypeId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<EncounterType> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(EncounterType aggregate) {
            store.remove(aggregate.id());
        }
    }
}
