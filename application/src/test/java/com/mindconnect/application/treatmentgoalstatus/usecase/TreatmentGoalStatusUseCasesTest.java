package com.mindconnect.application.treatmentgoalstatus.usecase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.treatmentgoalstatus.command.RegisterTreatmentGoalStatusCommand;
import com.mindconnect.application.treatmentgoalstatus.command.UpdateTreatmentGoalStatusCommand;
import com.mindconnect.application.treatmentgoalstatus.dto.TreatmentGoalStatusResponse;
import com.mindconnect.application.treatmentgoalstatus.exception.TreatmentGoalStatusNotFoundApplicationException;
import com.mindconnect.domain.treatmentgoalstatus.model.aggregate.TreatmentGoalStatus;
import com.mindconnect.domain.treatmentgoalstatus.model.valueobject.TreatmentGoalStatusId;
import com.mindconnect.domain.treatmentgoalstatus.port.repository.TreatmentGoalStatusRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TreatmentGoalStatusUseCasesTest {

    private FakeTreatmentGoalStatusRepository repository;
    private RegisterTreatmentGoalStatusUseCase register;
    private GetTreatmentGoalStatusByIdUseCase getById;
    private ListTreatmentGoalStatusUseCase list;
    private UpdateTreatmentGoalStatusUseCase update;

    @BeforeEach
    void setUp() {
        repository = new FakeTreatmentGoalStatusRepository();
        register = new RegisterTreatmentGoalStatusUseCase(repository);
        getById = new GetTreatmentGoalStatusByIdUseCase(repository);
        list = new ListTreatmentGoalStatusUseCase(repository);
        update = new UpdateTreatmentGoalStatusUseCase(repository);
    }

    @Test
    void registrarGuardaYDevuelveElRegistroConIdGenerado() {
        TreatmentGoalStatusResponse created = register.execute(commandA());

        assertNotNull(created.id());
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void buscarPorIdDevuelveLoGuardado() {
        TreatmentGoalStatusResponse created = register.execute(commandA());

        assertEquals(created, getById.execute(new TreatmentGoalStatusId(created.id())));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(TreatmentGoalStatusNotFoundApplicationException.class, () -> getById.execute(TreatmentGoalStatusId.generate()));
    }

    @Test
    void listarDevuelveTodosLosRegistros() {
        register.execute(commandA());
        register.execute(commandA());

        assertEquals(2, list.execute().size());
    }

    @Test
    void actualizarConservaElId() {
        TreatmentGoalStatusResponse created = register.execute(commandA());

        TreatmentGoalStatusResponse updated = update.execute(updateCommand(new TreatmentGoalStatusId(created.id())));

        assertEquals(created.id(), updated.id());
        assertEquals(updated, getById.execute(new TreatmentGoalStatusId(created.id())));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(TreatmentGoalStatusNotFoundApplicationException.class, () -> update.execute(updateCommand(TreatmentGoalStatusId.generate())));
    }

    private RegisterTreatmentGoalStatusCommand commandA() {
        return new RegisterTreatmentGoalStatusCommand("valor-a", "valor-a");
    }

    private UpdateTreatmentGoalStatusCommand updateCommand(TreatmentGoalStatusId id) {
        return new UpdateTreatmentGoalStatusCommand(id, "valor-b", "valor-b");
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeTreatmentGoalStatusRepository implements TreatmentGoalStatusRepository {
        private final Map<TreatmentGoalStatusId, TreatmentGoalStatus> store = new LinkedHashMap<>();

        @Override
        public TreatmentGoalStatus save(TreatmentGoalStatus aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<TreatmentGoalStatus> findById(TreatmentGoalStatusId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<TreatmentGoalStatus> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(TreatmentGoalStatus aggregate) {
            store.remove(aggregate.id());
        }
    }
}
