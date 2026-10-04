package com.mindconnect.application.encountermodality.usecase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.encountermodality.command.RegisterEncounterModalityCommand;
import com.mindconnect.application.encountermodality.command.UpdateEncounterModalityCommand;
import com.mindconnect.application.encountermodality.dto.EncounterModalityResponse;
import com.mindconnect.application.encountermodality.exception.EncounterModalityNotFoundApplicationException;
import com.mindconnect.domain.encountermodality.model.aggregate.EncounterModality;
import com.mindconnect.domain.encountermodality.model.valueobject.EncounterModalityId;
import com.mindconnect.domain.encountermodality.port.repository.EncounterModalityRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EncounterModalityUseCasesTest {

    private FakeEncounterModalityRepository repository;
    private RegisterEncounterModalityUseCase register;
    private GetEncounterModalityByIdUseCase getById;
    private ListEncounterModalityUseCase list;
    private UpdateEncounterModalityUseCase update;

    @BeforeEach
    void setUp() {
        repository = new FakeEncounterModalityRepository();
        register = new RegisterEncounterModalityUseCase(repository);
        getById = new GetEncounterModalityByIdUseCase(repository);
        list = new ListEncounterModalityUseCase(repository);
        update = new UpdateEncounterModalityUseCase(repository);
    }

    @Test
    void registrarGuardaYDevuelveElRegistroConIdGenerado() {
        EncounterModalityResponse created = register.execute(commandA());

        assertNotNull(created.id());
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void buscarPorIdDevuelveLoGuardado() {
        EncounterModalityResponse created = register.execute(commandA());

        assertEquals(created, getById.execute(new EncounterModalityId(created.id())));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(EncounterModalityNotFoundApplicationException.class, () -> getById.execute(EncounterModalityId.generate()));
    }

    @Test
    void listarDevuelveTodosLosRegistros() {
        register.execute(commandA());
        register.execute(commandA());

        assertEquals(2, list.execute().size());
    }

    @Test
    void actualizarConservaElId() {
        EncounterModalityResponse created = register.execute(commandA());

        EncounterModalityResponse updated = update.execute(updateCommand(new EncounterModalityId(created.id())));

        assertEquals(created.id(), updated.id());
        assertEquals(updated, getById.execute(new EncounterModalityId(created.id())));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(EncounterModalityNotFoundApplicationException.class, () -> update.execute(updateCommand(EncounterModalityId.generate())));
    }

    private RegisterEncounterModalityCommand commandA() {
        return new RegisterEncounterModalityCommand("valor-a", "valor-a");
    }

    private UpdateEncounterModalityCommand updateCommand(EncounterModalityId id) {
        return new UpdateEncounterModalityCommand(id, "valor-b", "valor-b");
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeEncounterModalityRepository implements EncounterModalityRepository {
        private final Map<EncounterModalityId, EncounterModality> store = new LinkedHashMap<>();

        @Override
        public EncounterModality save(EncounterModality aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<EncounterModality> findById(EncounterModalityId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<EncounterModality> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(EncounterModality aggregate) {
            store.remove(aggregate.id());
        }
    }
}
