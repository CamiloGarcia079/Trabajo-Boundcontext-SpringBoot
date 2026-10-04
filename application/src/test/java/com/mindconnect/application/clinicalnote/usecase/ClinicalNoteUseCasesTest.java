package com.mindconnect.application.clinicalnote.usecase;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.clinicalnote.command.RegisterClinicalNoteCommand;
import com.mindconnect.application.clinicalnote.command.UpdateClinicalNoteCommand;
import com.mindconnect.application.clinicalnote.dto.ClinicalNoteResponse;
import com.mindconnect.application.clinicalnote.exception.ClinicalNoteNotFoundApplicationException;
import com.mindconnect.domain.clinicalnote.model.aggregate.ClinicalNote;
import com.mindconnect.domain.clinicalnote.model.valueobject.ClinicalNoteId;
import com.mindconnect.domain.clinicalnote.port.repository.ClinicalNoteRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ClinicalNoteUseCasesTest {

    private FakeClinicalNoteRepository repository;
    private RegisterClinicalNoteUseCase register;
    private GetClinicalNoteByIdUseCase getById;
    private ListClinicalNoteUseCase list;
    private UpdateClinicalNoteUseCase update;

    @BeforeEach
    void setUp() {
        repository = new FakeClinicalNoteRepository();
        register = new RegisterClinicalNoteUseCase(repository);
        getById = new GetClinicalNoteByIdUseCase(repository);
        list = new ListClinicalNoteUseCase(repository);
        update = new UpdateClinicalNoteUseCase(repository);
    }

    @Test
    void registrarGuardaYDevuelveElRegistroConIdGenerado() {
        ClinicalNoteResponse created = register.execute(commandA());

        assertNotNull(created.id());
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void buscarPorIdDevuelveLoGuardado() {
        ClinicalNoteResponse created = register.execute(commandA());

        assertEquals(created, getById.execute(new ClinicalNoteId(created.id())));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(ClinicalNoteNotFoundApplicationException.class, () -> getById.execute(ClinicalNoteId.generate()));
    }

    @Test
    void listarDevuelveTodosLosRegistros() {
        register.execute(commandA());
        register.execute(commandA());

        assertEquals(2, list.execute().size());
    }

    @Test
    void actualizarConservaElId() {
        ClinicalNoteResponse created = register.execute(commandA());

        ClinicalNoteResponse updated = update.execute(updateCommand(new ClinicalNoteId(created.id())));

        assertEquals(created.id(), updated.id());
        assertEquals(updated, getById.execute(new ClinicalNoteId(created.id())));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(ClinicalNoteNotFoundApplicationException.class, () -> update.execute(updateCommand(ClinicalNoteId.generate())));
    }

    private RegisterClinicalNoteCommand commandA() {
        return new RegisterClinicalNoteCommand(UUID.randomUUID(), UUID.randomUUID(), "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", LocalDateTime.now());
    }

    private UpdateClinicalNoteCommand updateCommand(ClinicalNoteId id) {
        return new UpdateClinicalNoteCommand(id, UUID.randomUUID(), UUID.randomUUID(), "valor-b", "valor-b", "valor-b", "valor-b", "valor-b", LocalDateTime.now());
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeClinicalNoteRepository implements ClinicalNoteRepository {
        private final Map<ClinicalNoteId, ClinicalNote> store = new LinkedHashMap<>();

        @Override
        public ClinicalNote save(ClinicalNote aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<ClinicalNote> findById(ClinicalNoteId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<ClinicalNote> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(ClinicalNote aggregate) {
            store.remove(aggregate.id());
        }
    }
}
