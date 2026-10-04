package com.mindconnect.application.treatmentgoal.usecase;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.treatmentgoal.command.RegisterTreatmentGoalCommand;
import com.mindconnect.application.treatmentgoal.command.UpdateTreatmentGoalCommand;
import com.mindconnect.application.treatmentgoal.dto.TreatmentGoalResponse;
import com.mindconnect.application.treatmentgoal.exception.TreatmentGoalNotFoundApplicationException;
import com.mindconnect.domain.treatmentgoal.model.aggregate.TreatmentGoal;
import com.mindconnect.domain.treatmentgoal.model.valueobject.TreatmentGoalId;
import com.mindconnect.domain.treatmentgoal.port.repository.TreatmentGoalRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TreatmentGoalUseCasesTest {

    private FakeTreatmentGoalRepository repository;
    private RegisterTreatmentGoalUseCase register;
    private GetTreatmentGoalByIdUseCase getById;
    private ListTreatmentGoalUseCase list;
    private UpdateTreatmentGoalUseCase update;

    @BeforeEach
    void setUp() {
        repository = new FakeTreatmentGoalRepository();
        register = new RegisterTreatmentGoalUseCase(repository);
        getById = new GetTreatmentGoalByIdUseCase(repository);
        list = new ListTreatmentGoalUseCase(repository);
        update = new UpdateTreatmentGoalUseCase(repository);
    }

    @Test
    void registrarGuardaYDevuelveElRegistroConIdGenerado() {
        TreatmentGoalResponse created = register.execute(commandA());

        assertNotNull(created.id());
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void buscarPorIdDevuelveLoGuardado() {
        TreatmentGoalResponse created = register.execute(commandA());

        assertEquals(created, getById.execute(new TreatmentGoalId(created.id())));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(TreatmentGoalNotFoundApplicationException.class, () -> getById.execute(TreatmentGoalId.generate()));
    }

    @Test
    void listarDevuelveTodosLosRegistros() {
        register.execute(commandA());
        register.execute(commandA());

        assertEquals(2, list.execute().size());
    }

    @Test
    void actualizarConservaElId() {
        TreatmentGoalResponse created = register.execute(commandA());

        TreatmentGoalResponse updated = update.execute(updateCommand(new TreatmentGoalId(created.id())));

        assertEquals(created.id(), updated.id());
        assertEquals(updated, getById.execute(new TreatmentGoalId(created.id())));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(TreatmentGoalNotFoundApplicationException.class, () -> update.execute(updateCommand(TreatmentGoalId.generate())));
    }

    private RegisterTreatmentGoalCommand commandA() {
        return new RegisterTreatmentGoalCommand(UUID.randomUUID(), "valor-a", LocalDate.now(), LocalDateTime.now(), "valor-a", UUID.randomUUID());
    }

    private UpdateTreatmentGoalCommand updateCommand(TreatmentGoalId id) {
        return new UpdateTreatmentGoalCommand(id, UUID.randomUUID(), "valor-b", LocalDate.now(), LocalDateTime.now(), "valor-b", UUID.randomUUID());
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeTreatmentGoalRepository implements TreatmentGoalRepository {
        private final Map<TreatmentGoalId, TreatmentGoal> store = new LinkedHashMap<>();

        @Override
        public TreatmentGoal save(TreatmentGoal aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<TreatmentGoal> findById(TreatmentGoalId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<TreatmentGoal> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(TreatmentGoal aggregate) {
            store.remove(aggregate.id());
        }
    }
}
