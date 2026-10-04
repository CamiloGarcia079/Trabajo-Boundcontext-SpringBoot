package com.mindconnect.application.patientcontact.usecase;

import java.util.UUID;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.patientcontact.command.RegisterPatientContactCommand;
import com.mindconnect.application.patientcontact.command.UpdatePatientContactCommand;
import com.mindconnect.application.patientcontact.dto.PatientContactResponse;
import com.mindconnect.application.patientcontact.exception.PatientContactNotFoundApplicationException;
import com.mindconnect.domain.patientcontact.model.aggregate.PatientContact;
import com.mindconnect.domain.patientcontact.model.valueobject.PatientContactId;
import com.mindconnect.domain.patientcontact.port.repository.PatientContactRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PatientContactUseCasesTest {

    private FakePatientContactRepository repository;
    private RegisterPatientContactUseCase register;
    private GetPatientContactByIdUseCase getById;
    private ListPatientContactUseCase list;
    private UpdatePatientContactUseCase update;

    @BeforeEach
    void setUp() {
        repository = new FakePatientContactRepository();
        register = new RegisterPatientContactUseCase(repository);
        getById = new GetPatientContactByIdUseCase(repository);
        list = new ListPatientContactUseCase(repository);
        update = new UpdatePatientContactUseCase(repository);
    }

    @Test
    void registrarGuardaYDevuelveElRegistroConIdGenerado() {
        PatientContactResponse created = register.execute(commandA());

        assertNotNull(created.id());
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void buscarPorIdDevuelveLoGuardado() {
        PatientContactResponse created = register.execute(commandA());

        assertEquals(created, getById.execute(new PatientContactId(created.id())));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(PatientContactNotFoundApplicationException.class, () -> getById.execute(PatientContactId.generate()));
    }

    @Test
    void listarDevuelveTodosLosRegistros() {
        register.execute(commandA());
        register.execute(commandA());

        assertEquals(2, list.execute().size());
    }

    @Test
    void actualizarConservaElId() {
        PatientContactResponse created = register.execute(commandA());

        PatientContactResponse updated = update.execute(updateCommand(new PatientContactId(created.id())));

        assertEquals(created.id(), updated.id());
        assertEquals(updated, getById.execute(new PatientContactId(created.id())));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(PatientContactNotFoundApplicationException.class, () -> update.execute(updateCommand(PatientContactId.generate())));
    }

    private RegisterPatientContactCommand commandA() {
        return new RegisterPatientContactCommand(UUID.randomUUID(), UUID.randomUUID(), true, true, UUID.randomUUID());
    }

    private UpdatePatientContactCommand updateCommand(PatientContactId id) {
        return new UpdatePatientContactCommand(id, UUID.randomUUID(), UUID.randomUUID(), false, false, UUID.randomUUID());
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakePatientContactRepository implements PatientContactRepository {
        private final Map<PatientContactId, PatientContact> store = new LinkedHashMap<>();

        @Override
        public PatientContact save(PatientContact aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<PatientContact> findById(PatientContactId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<PatientContact> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(PatientContact aggregate) {
            store.remove(aggregate.id());
        }
    }
}
