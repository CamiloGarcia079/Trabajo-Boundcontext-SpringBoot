package com.mindconnect.application.patientallergy.usecase;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.patientallergy.command.RegisterPatientAllergyCommand;
import com.mindconnect.application.patientallergy.command.UpdatePatientAllergyCommand;
import com.mindconnect.application.patientallergy.dto.PatientAllergyResponse;
import com.mindconnect.application.patientallergy.exception.PatientAllergyNotFoundApplicationException;
import com.mindconnect.domain.patientallergy.model.aggregate.PatientAllergy;
import com.mindconnect.domain.patientallergy.model.valueobject.PatientAllergyId;
import com.mindconnect.domain.patientallergy.port.repository.PatientAllergyRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PatientAllergyUseCasesTest {

    private FakePatientAllergyRepository repository;
    private RegisterPatientAllergyUseCase register;
    private GetPatientAllergyByIdUseCase getById;
    private ListPatientAllergyUseCase list;
    private UpdatePatientAllergyUseCase update;

    @BeforeEach
    void setUp() {
        repository = new FakePatientAllergyRepository();
        register = new RegisterPatientAllergyUseCase(repository);
        getById = new GetPatientAllergyByIdUseCase(repository);
        list = new ListPatientAllergyUseCase(repository);
        update = new UpdatePatientAllergyUseCase(repository);
    }

    @Test
    void registrarGuardaYDevuelveElRegistroConIdGenerado() {
        PatientAllergyResponse created = register.execute(commandA());

        assertNotNull(created.id());
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void buscarPorIdDevuelveLoGuardado() {
        PatientAllergyResponse created = register.execute(commandA());

        assertEquals(created, getById.execute(new PatientAllergyId(created.id())));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(PatientAllergyNotFoundApplicationException.class, () -> getById.execute(PatientAllergyId.generate()));
    }

    @Test
    void listarDevuelveTodosLosRegistros() {
        register.execute(commandA());
        register.execute(commandA());

        assertEquals(2, list.execute().size());
    }

    @Test
    void actualizarConservaElId() {
        PatientAllergyResponse created = register.execute(commandA());

        PatientAllergyResponse updated = update.execute(updateCommand(new PatientAllergyId(created.id())));

        assertEquals(created.id(), updated.id());
        assertEquals(updated, getById.execute(new PatientAllergyId(created.id())));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(PatientAllergyNotFoundApplicationException.class, () -> update.execute(updateCommand(PatientAllergyId.generate())));
    }

    private RegisterPatientAllergyCommand commandA() {
        return new RegisterPatientAllergyCommand(UUID.randomUUID(), "valor-a", "valor-a", "valor-a", LocalDateTime.now(), UUID.randomUUID());
    }

    private UpdatePatientAllergyCommand updateCommand(PatientAllergyId id) {
        return new UpdatePatientAllergyCommand(id, UUID.randomUUID(), "valor-b", "valor-b", "valor-b", LocalDateTime.now(), UUID.randomUUID());
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakePatientAllergyRepository implements PatientAllergyRepository {
        private final Map<PatientAllergyId, PatientAllergy> store = new LinkedHashMap<>();

        @Override
        public PatientAllergy save(PatientAllergy aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<PatientAllergy> findById(PatientAllergyId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<PatientAllergy> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(PatientAllergy aggregate) {
            store.remove(aggregate.id());
        }
    }
}
