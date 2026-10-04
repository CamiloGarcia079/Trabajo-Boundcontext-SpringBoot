package com.mindconnect.application.professionalstudy.usecase;

import java.util.UUID;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.professionalstudy.command.RegisterProfessionalStudyCommand;
import com.mindconnect.application.professionalstudy.command.UpdateProfessionalStudyCommand;
import com.mindconnect.application.professionalstudy.dto.ProfessionalStudyResponse;
import com.mindconnect.application.professionalstudy.exception.ProfessionalStudyNotFoundApplicationException;
import com.mindconnect.domain.professionalstudy.model.aggregate.ProfessionalStudy;
import com.mindconnect.domain.professionalstudy.model.valueobject.ProfessionalStudyId;
import com.mindconnect.domain.professionalstudy.port.repository.ProfessionalStudyRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProfessionalStudyUseCasesTest {

    private FakeProfessionalStudyRepository repository;
    private RegisterProfessionalStudyUseCase register;
    private GetProfessionalStudyByIdUseCase getById;
    private ListProfessionalStudyUseCase list;
    private UpdateProfessionalStudyUseCase update;

    @BeforeEach
    void setUp() {
        repository = new FakeProfessionalStudyRepository();
        register = new RegisterProfessionalStudyUseCase(repository);
        getById = new GetProfessionalStudyByIdUseCase(repository);
        list = new ListProfessionalStudyUseCase(repository);
        update = new UpdateProfessionalStudyUseCase(repository);
    }

    @Test
    void registrarGuardaYDevuelveElRegistroConIdGenerado() {
        ProfessionalStudyResponse created = register.execute(commandA());

        assertNotNull(created.id());
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void buscarPorIdDevuelveLoGuardado() {
        ProfessionalStudyResponse created = register.execute(commandA());

        assertEquals(created, getById.execute(new ProfessionalStudyId(created.id())));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(ProfessionalStudyNotFoundApplicationException.class, () -> getById.execute(ProfessionalStudyId.generate()));
    }

    @Test
    void listarDevuelveTodosLosRegistros() {
        register.execute(commandA());
        register.execute(commandA());

        assertEquals(2, list.execute().size());
    }

    @Test
    void actualizarConservaElId() {
        ProfessionalStudyResponse created = register.execute(commandA());

        ProfessionalStudyResponse updated = update.execute(updateCommand(new ProfessionalStudyId(created.id())));

        assertEquals(created.id(), updated.id());
        assertEquals(updated, getById.execute(new ProfessionalStudyId(created.id())));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(ProfessionalStudyNotFoundApplicationException.class, () -> update.execute(updateCommand(ProfessionalStudyId.generate())));
    }

    private RegisterProfessionalStudyCommand commandA() {
        return new RegisterProfessionalStudyCommand(UUID.randomUUID(), UUID.randomUUID(), "valor-a", "valor-a", true, "valor-a", UUID.randomUUID());
    }

    private UpdateProfessionalStudyCommand updateCommand(ProfessionalStudyId id) {
        return new UpdateProfessionalStudyCommand(id, UUID.randomUUID(), UUID.randomUUID(), "valor-b", "valor-b", false, "valor-b", UUID.randomUUID());
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeProfessionalStudyRepository implements ProfessionalStudyRepository {
        private final Map<ProfessionalStudyId, ProfessionalStudy> store = new LinkedHashMap<>();

        @Override
        public ProfessionalStudy save(ProfessionalStudy aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<ProfessionalStudy> findById(ProfessionalStudyId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<ProfessionalStudy> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(ProfessionalStudy aggregate) {
            store.remove(aggregate.id());
        }
    }
}
