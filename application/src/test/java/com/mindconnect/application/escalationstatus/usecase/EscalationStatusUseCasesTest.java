package com.mindconnect.application.escalationstatus.usecase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.escalationstatus.command.RegisterEscalationStatusCommand;
import com.mindconnect.application.escalationstatus.command.UpdateEscalationStatusCommand;
import com.mindconnect.application.escalationstatus.dto.EscalationStatusResponse;
import com.mindconnect.application.escalationstatus.exception.EscalationStatusNotFoundApplicationException;
import com.mindconnect.domain.escalationstatus.model.aggregate.EscalationStatus;
import com.mindconnect.domain.escalationstatus.model.valueobject.EscalationStatusId;
import com.mindconnect.domain.escalationstatus.port.repository.EscalationStatusRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EscalationStatusUseCasesTest {

    private FakeEscalationStatusRepository repository;
    private RegisterEscalationStatusUseCase register;
    private GetEscalationStatusByIdUseCase getById;
    private ListEscalationStatusUseCase list;
    private UpdateEscalationStatusUseCase update;

    @BeforeEach
    void setUp() {
        repository = new FakeEscalationStatusRepository();
        register = new RegisterEscalationStatusUseCase(repository);
        getById = new GetEscalationStatusByIdUseCase(repository);
        list = new ListEscalationStatusUseCase(repository);
        update = new UpdateEscalationStatusUseCase(repository);
    }

    @Test
    void registrarGuardaYDevuelveElRegistroConIdGenerado() {
        EscalationStatusResponse created = register.execute(commandA());

        assertNotNull(created.id());
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void buscarPorIdDevuelveLoGuardado() {
        EscalationStatusResponse created = register.execute(commandA());

        assertEquals(created, getById.execute(new EscalationStatusId(created.id())));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(EscalationStatusNotFoundApplicationException.class, () -> getById.execute(EscalationStatusId.generate()));
    }

    @Test
    void listarDevuelveTodosLosRegistros() {
        register.execute(commandA());
        register.execute(commandA());

        assertEquals(2, list.execute().size());
    }

    @Test
    void actualizarConservaElId() {
        EscalationStatusResponse created = register.execute(commandA());

        EscalationStatusResponse updated = update.execute(updateCommand(new EscalationStatusId(created.id())));

        assertEquals(created.id(), updated.id());
        assertEquals(updated, getById.execute(new EscalationStatusId(created.id())));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(EscalationStatusNotFoundApplicationException.class, () -> update.execute(updateCommand(EscalationStatusId.generate())));
    }

    private RegisterEscalationStatusCommand commandA() {
        return new RegisterEscalationStatusCommand("valor-a");
    }

    private UpdateEscalationStatusCommand updateCommand(EscalationStatusId id) {
        return new UpdateEscalationStatusCommand(id, "valor-b");
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeEscalationStatusRepository implements EscalationStatusRepository {
        private final Map<EscalationStatusId, EscalationStatus> store = new LinkedHashMap<>();

        @Override
        public EscalationStatus save(EscalationStatus aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<EscalationStatus> findById(EscalationStatusId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<EscalationStatus> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(EscalationStatus aggregate) {
            store.remove(aggregate.id());
        }
    }
}
