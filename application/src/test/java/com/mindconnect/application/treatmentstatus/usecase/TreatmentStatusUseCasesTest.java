package com.mindconnect.application.treatmentstatus.usecase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.treatmentstatus.command.RegisterTreatmentStatusCommand;
import com.mindconnect.application.treatmentstatus.command.UpdateTreatmentStatusCommand;
import com.mindconnect.application.treatmentstatus.dto.TreatmentStatusResponse;
import com.mindconnect.application.treatmentstatus.exception.TreatmentStatusNotFoundApplicationException;
import com.mindconnect.domain.treatmentstatus.model.aggregate.TreatmentStatus;
import com.mindconnect.domain.treatmentstatus.model.valueobject.TreatmentStatusId;
import com.mindconnect.domain.treatmentstatus.port.repository.TreatmentStatusRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TreatmentStatusUseCasesTest {

    private FakeTreatmentStatusRepository repository;
    private RegisterTreatmentStatusUseCase register;
    private GetTreatmentStatusByIdUseCase getById;
    private ListTreatmentStatusUseCase list;
    private UpdateTreatmentStatusUseCase update;

    @BeforeEach
    void setUp() {
        repository = new FakeTreatmentStatusRepository();
        register = new RegisterTreatmentStatusUseCase(repository);
        getById = new GetTreatmentStatusByIdUseCase(repository);
        list = new ListTreatmentStatusUseCase(repository);
        update = new UpdateTreatmentStatusUseCase(repository);
    }

    @Test
    void registrarGuardaYDevuelveElRegistroConIdGenerado() {
        TreatmentStatusResponse created = register.execute(commandA());

        assertNotNull(created.id());
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void buscarPorIdDevuelveLoGuardado() {
        TreatmentStatusResponse created = register.execute(commandA());

        assertEquals(created, getById.execute(new TreatmentStatusId(created.id())));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(TreatmentStatusNotFoundApplicationException.class, () -> getById.execute(TreatmentStatusId.generate()));
    }

    @Test
    void listarDevuelveTodosLosRegistros() {
        register.execute(commandA());
        register.execute(commandA());

        assertEquals(2, list.execute().size());
    }

    @Test
    void actualizarConservaElId() {
        TreatmentStatusResponse created = register.execute(commandA());

        TreatmentStatusResponse updated = update.execute(updateCommand(new TreatmentStatusId(created.id())));

        assertEquals(created.id(), updated.id());
        assertEquals(updated, getById.execute(new TreatmentStatusId(created.id())));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(TreatmentStatusNotFoundApplicationException.class, () -> update.execute(updateCommand(TreatmentStatusId.generate())));
    }

    private RegisterTreatmentStatusCommand commandA() {
        return new RegisterTreatmentStatusCommand("valor-a", "valor-a");
    }

    private UpdateTreatmentStatusCommand updateCommand(TreatmentStatusId id) {
        return new UpdateTreatmentStatusCommand(id, "valor-b", "valor-b");
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeTreatmentStatusRepository implements TreatmentStatusRepository {
        private final Map<TreatmentStatusId, TreatmentStatus> store = new LinkedHashMap<>();

        @Override
        public TreatmentStatus save(TreatmentStatus aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<TreatmentStatus> findById(TreatmentStatusId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<TreatmentStatus> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(TreatmentStatus aggregate) {
            store.remove(aggregate.id());
        }
    }
}
