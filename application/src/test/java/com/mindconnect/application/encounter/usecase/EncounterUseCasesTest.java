package com.mindconnect.application.encounter.usecase;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.encounter.command.RegisterEncounterCommand;
import com.mindconnect.application.encounter.command.UpdateEncounterCommand;
import com.mindconnect.application.encounter.dto.EncounterResponse;
import com.mindconnect.application.encounter.exception.EncounterNotFoundApplicationException;
import com.mindconnect.domain.encounter.model.aggregate.Encounter;
import com.mindconnect.domain.encounter.model.valueobject.EncounterId;
import com.mindconnect.domain.encounter.port.repository.EncounterRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EncounterUseCasesTest {

    private FakeEncounterRepository repository;
    private RegisterEncounterUseCase register;
    private GetEncounterByIdUseCase getById;
    private ListEncounterUseCase list;
    private UpdateEncounterUseCase update;

    @BeforeEach
    void setUp() {
        repository = new FakeEncounterRepository();
        register = new RegisterEncounterUseCase(repository);
        getById = new GetEncounterByIdUseCase(repository);
        list = new ListEncounterUseCase(repository);
        update = new UpdateEncounterUseCase(repository);
    }

    @Test
    void registrarGuardaYDevuelveElRegistroConIdGenerado() {
        EncounterResponse created = register.execute(commandA());

        assertNotNull(created.id());
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void buscarPorIdDevuelveLoGuardado() {
        EncounterResponse created = register.execute(commandA());

        assertEquals(created, getById.execute(new EncounterId(created.id())));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(EncounterNotFoundApplicationException.class, () -> getById.execute(EncounterId.generate()));
    }

    @Test
    void listarDevuelveTodosLosRegistros() {
        register.execute(commandA());
        register.execute(commandA());

        assertEquals(2, list.execute().size());
    }

    @Test
    void actualizarConservaElId() {
        EncounterResponse created = register.execute(commandA());

        EncounterResponse updated = update.execute(updateCommand(new EncounterId(created.id())));

        assertEquals(created.id(), updated.id());
        assertEquals(updated, getById.execute(new EncounterId(created.id())));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(EncounterNotFoundApplicationException.class, () -> update.execute(updateCommand(EncounterId.generate())));
    }

    private RegisterEncounterCommand commandA() {
        return new RegisterEncounterCommand(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), LocalDateTime.now(), LocalDateTime.now(), "valor-a", "valor-a", UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());
    }

    private UpdateEncounterCommand updateCommand(EncounterId id) {
        return new UpdateEncounterCommand(id, UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), LocalDateTime.now(), LocalDateTime.now(), "valor-b", "valor-b", UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeEncounterRepository implements EncounterRepository {
        private final Map<EncounterId, Encounter> store = new LinkedHashMap<>();

        @Override
        public Encounter save(Encounter aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<Encounter> findById(EncounterId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<Encounter> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(Encounter aggregate) {
            store.remove(aggregate.id());
        }
    }
}
