package com.mindconnect.application.patient.usecase;

import java.time.LocalDate;
import java.util.UUID;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.patient.command.RegisterPatientCommand;
import com.mindconnect.application.patient.command.UpdatePatientCommand;
import com.mindconnect.application.patient.dto.PatientResponse;
import com.mindconnect.application.patient.exception.PatientNotFoundApplicationException;
import com.mindconnect.domain.patient.model.aggregate.Patient;
import com.mindconnect.domain.patient.model.valueobject.PatientId;
import com.mindconnect.domain.patient.port.repository.PatientRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PatientUseCasesTest {

    private FakePatientRepository repository;
    private RegisterPatientUseCase register;
    private GetPatientByIdUseCase getById;
    private ListPatientUseCase list;
    private UpdatePatientUseCase update;

    @BeforeEach
    void setUp() {
        repository = new FakePatientRepository();
        register = new RegisterPatientUseCase(repository);
        getById = new GetPatientByIdUseCase(repository);
        list = new ListPatientUseCase(repository);
        update = new UpdatePatientUseCase(repository);
    }

    @Test
    void registrarGuardaYDevuelveElRegistroConIdGenerado() {
        PatientResponse created = register.execute(commandA());

        assertNotNull(created.id());
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void buscarPorIdDevuelveLoGuardado() {
        PatientResponse created = register.execute(commandA());

        assertEquals(created, getById.execute(new PatientId(created.id())));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(PatientNotFoundApplicationException.class, () -> getById.execute(PatientId.generate()));
    }

    @Test
    void listarDevuelveTodosLosRegistros() {
        register.execute(commandA());
        register.execute(commandA());

        assertEquals(2, list.execute().size());
    }

    @Test
    void actualizarConservaElId() {
        PatientResponse created = register.execute(commandA());

        PatientResponse updated = update.execute(updateCommand(new PatientId(created.id())));

        assertEquals(created.id(), updated.id());
        assertEquals(updated, getById.execute(new PatientId(created.id())));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(PatientNotFoundApplicationException.class, () -> update.execute(updateCommand(PatientId.generate())));
    }

    private RegisterPatientCommand commandA() {
        return new RegisterPatientCommand(UUID.randomUUID(), "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", LocalDate.now(), UUID.randomUUID(), UUID.randomUUID(), "valor-a", "valor-a", "valor-a", UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());
    }

    private UpdatePatientCommand updateCommand(PatientId id) {
        return new UpdatePatientCommand(id, UUID.randomUUID(), "valor-b", "valor-b", "valor-b", "valor-b", "valor-b", LocalDate.now(), UUID.randomUUID(), UUID.randomUUID(), "valor-b", "valor-b", "valor-b", UUID.randomUUID(), UUID.randomUUID());
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakePatientRepository implements PatientRepository {
        private final Map<PatientId, Patient> store = new LinkedHashMap<>();

        @Override
        public Patient save(Patient aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<Patient> findById(PatientId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<Patient> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(Patient aggregate) {
            store.remove(aggregate.id());
        }
    }
}
