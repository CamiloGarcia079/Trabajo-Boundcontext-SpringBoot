package com.mindconnect.application.clinicalrecord.usecase;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.clinicalrecord.command.RegisterClinicalRecordCommand;
import com.mindconnect.application.clinicalrecord.command.UpdateClinicalRecordCommand;
import com.mindconnect.application.clinicalrecord.dto.ClinicalRecordResponse;
import com.mindconnect.application.clinicalrecord.exception.ClinicalRecordNotFoundApplicationException;
import com.mindconnect.domain.clinicalrecord.model.aggregate.ClinicalRecord;
import com.mindconnect.domain.clinicalrecord.model.valueobject.ClinicalRecordId;
import com.mindconnect.domain.clinicalrecord.port.repository.ClinicalRecordRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ClinicalRecordUseCasesTest {

    private FakeClinicalRecordRepository repository;
    private RegisterClinicalRecordUseCase register;
    private GetClinicalRecordByIdUseCase getById;
    private ListClinicalRecordUseCase list;
    private UpdateClinicalRecordUseCase update;

    @BeforeEach
    void setUp() {
        repository = new FakeClinicalRecordRepository();
        register = new RegisterClinicalRecordUseCase(repository);
        getById = new GetClinicalRecordByIdUseCase(repository);
        list = new ListClinicalRecordUseCase(repository);
        update = new UpdateClinicalRecordUseCase(repository);
    }

    @Test
    void registrarGuardaYDevuelveElRegistroConIdGenerado() {
        ClinicalRecordResponse created = register.execute(commandA());

        assertNotNull(created.id());
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void buscarPorIdDevuelveLoGuardado() {
        ClinicalRecordResponse created = register.execute(commandA());

        assertEquals(created, getById.execute(new ClinicalRecordId(created.id())));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(ClinicalRecordNotFoundApplicationException.class, () -> getById.execute(ClinicalRecordId.generate()));
    }

    @Test
    void listarDevuelveTodosLosRegistros() {
        register.execute(commandA());
        register.execute(commandA());

        assertEquals(2, list.execute().size());
    }

    @Test
    void actualizarConservaElId() {
        ClinicalRecordResponse created = register.execute(commandA());

        ClinicalRecordResponse updated = update.execute(updateCommand(new ClinicalRecordId(created.id())));

        assertEquals(created.id(), updated.id());
        assertEquals(updated, getById.execute(new ClinicalRecordId(created.id())));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(ClinicalRecordNotFoundApplicationException.class, () -> update.execute(updateCommand(ClinicalRecordId.generate())));
    }

    private RegisterClinicalRecordCommand commandA() {
        return new RegisterClinicalRecordCommand(UUID.randomUUID(), LocalDateTime.now(), "valor-a", LocalDateTime.now(), LocalDateTime.now(), UUID.randomUUID(), UUID.randomUUID());
    }

    private UpdateClinicalRecordCommand updateCommand(ClinicalRecordId id) {
        return new UpdateClinicalRecordCommand(id, UUID.randomUUID(), LocalDateTime.now(), "valor-b", LocalDateTime.now(), LocalDateTime.now(), UUID.randomUUID());
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeClinicalRecordRepository implements ClinicalRecordRepository {
        private final Map<ClinicalRecordId, ClinicalRecord> store = new LinkedHashMap<>();

        @Override
        public ClinicalRecord save(ClinicalRecord aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<ClinicalRecord> findById(ClinicalRecordId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<ClinicalRecord> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(ClinicalRecord aggregate) {
            store.remove(aggregate.id());
        }
    }
}
