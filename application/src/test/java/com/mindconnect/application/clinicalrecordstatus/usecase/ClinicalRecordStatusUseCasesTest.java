package com.mindconnect.application.clinicalrecordstatus.usecase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.clinicalrecordstatus.command.RegisterClinicalRecordStatusCommand;
import com.mindconnect.application.clinicalrecordstatus.command.UpdateClinicalRecordStatusCommand;
import com.mindconnect.application.clinicalrecordstatus.dto.ClinicalRecordStatusResponse;
import com.mindconnect.application.clinicalrecordstatus.exception.ClinicalRecordStatusNotFoundApplicationException;
import com.mindconnect.domain.clinicalrecordstatus.model.aggregate.ClinicalRecordStatus;
import com.mindconnect.domain.clinicalrecordstatus.model.valueobject.ClinicalRecordStatusId;
import com.mindconnect.domain.clinicalrecordstatus.port.repository.ClinicalRecordStatusRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ClinicalRecordStatusUseCasesTest {

    private FakeClinicalRecordStatusRepository repository;
    private RegisterClinicalRecordStatusUseCase register;
    private GetClinicalRecordStatusByIdUseCase getById;
    private ListClinicalRecordStatusUseCase list;
    private UpdateClinicalRecordStatusUseCase update;

    @BeforeEach
    void setUp() {
        repository = new FakeClinicalRecordStatusRepository();
        register = new RegisterClinicalRecordStatusUseCase(repository);
        getById = new GetClinicalRecordStatusByIdUseCase(repository);
        list = new ListClinicalRecordStatusUseCase(repository);
        update = new UpdateClinicalRecordStatusUseCase(repository);
    }

    @Test
    void registrarGuardaYDevuelveElRegistroConIdGenerado() {
        ClinicalRecordStatusResponse created = register.execute(commandA());

        assertNotNull(created.id());
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void buscarPorIdDevuelveLoGuardado() {
        ClinicalRecordStatusResponse created = register.execute(commandA());

        assertEquals(created, getById.execute(new ClinicalRecordStatusId(created.id())));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(ClinicalRecordStatusNotFoundApplicationException.class, () -> getById.execute(ClinicalRecordStatusId.generate()));
    }

    @Test
    void listarDevuelveTodosLosRegistros() {
        register.execute(commandA());
        register.execute(commandA());

        assertEquals(2, list.execute().size());
    }

    @Test
    void actualizarConservaElId() {
        ClinicalRecordStatusResponse created = register.execute(commandA());

        ClinicalRecordStatusResponse updated = update.execute(updateCommand(new ClinicalRecordStatusId(created.id())));

        assertEquals(created.id(), updated.id());
        assertEquals(updated, getById.execute(new ClinicalRecordStatusId(created.id())));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(ClinicalRecordStatusNotFoundApplicationException.class, () -> update.execute(updateCommand(ClinicalRecordStatusId.generate())));
    }

    private RegisterClinicalRecordStatusCommand commandA() {
        return new RegisterClinicalRecordStatusCommand("valor-a", "valor-a");
    }

    private UpdateClinicalRecordStatusCommand updateCommand(ClinicalRecordStatusId id) {
        return new UpdateClinicalRecordStatusCommand(id, "valor-b", "valor-b");
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeClinicalRecordStatusRepository implements ClinicalRecordStatusRepository {
        private final Map<ClinicalRecordStatusId, ClinicalRecordStatus> store = new LinkedHashMap<>();

        @Override
        public ClinicalRecordStatus save(ClinicalRecordStatus aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<ClinicalRecordStatus> findById(ClinicalRecordStatusId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<ClinicalRecordStatus> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(ClinicalRecordStatus aggregate) {
            store.remove(aggregate.id());
        }
    }
}
