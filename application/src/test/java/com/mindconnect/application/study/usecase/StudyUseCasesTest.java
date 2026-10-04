package com.mindconnect.application.study.usecase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.study.command.RegisterStudyCommand;
import com.mindconnect.application.study.command.UpdateStudyCommand;
import com.mindconnect.application.study.dto.StudyResponse;
import com.mindconnect.application.study.exception.StudyNotFoundApplicationException;
import com.mindconnect.domain.study.model.aggregate.Study;
import com.mindconnect.domain.study.model.valueobject.StudyId;
import com.mindconnect.domain.study.port.repository.StudyRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StudyUseCasesTest {

    private FakeStudyRepository repository;
    private RegisterStudyUseCase register;
    private GetStudyByIdUseCase getById;
    private ListStudyUseCase list;
    private UpdateStudyUseCase update;

    @BeforeEach
    void setUp() {
        repository = new FakeStudyRepository();
        register = new RegisterStudyUseCase(repository);
        getById = new GetStudyByIdUseCase(repository);
        list = new ListStudyUseCase(repository);
        update = new UpdateStudyUseCase(repository);
    }

    @Test
    void registrarGuardaYDevuelveElRegistroConIdGenerado() {
        StudyResponse created = register.execute(commandA());

        assertNotNull(created.id());
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void buscarPorIdDevuelveLoGuardado() {
        StudyResponse created = register.execute(commandA());

        assertEquals(created, getById.execute(new StudyId(created.id())));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(StudyNotFoundApplicationException.class, () -> getById.execute(StudyId.generate()));
    }

    @Test
    void listarDevuelveTodosLosRegistros() {
        register.execute(commandA());
        register.execute(commandA());

        assertEquals(2, list.execute().size());
    }

    @Test
    void actualizarConservaElId() {
        StudyResponse created = register.execute(commandA());

        StudyResponse updated = update.execute(updateCommand(new StudyId(created.id())));

        assertEquals(created.id(), updated.id());
        assertEquals(updated, getById.execute(new StudyId(created.id())));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(StudyNotFoundApplicationException.class, () -> update.execute(updateCommand(StudyId.generate())));
    }

    private RegisterStudyCommand commandA() {
        return new RegisterStudyCommand("valor-a");
    }

    private UpdateStudyCommand updateCommand(StudyId id) {
        return new UpdateStudyCommand(id, "valor-b");
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeStudyRepository implements StudyRepository {
        private final Map<StudyId, Study> store = new LinkedHashMap<>();

        @Override
        public Study save(Study aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<Study> findById(StudyId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<Study> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(Study aggregate) {
            store.remove(aggregate.id());
        }
    }
}
