package com.mindconnect.application.assessmenttype.usecase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.assessmenttype.command.RegisterAssessmentTypeCommand;
import com.mindconnect.application.assessmenttype.command.UpdateAssessmentTypeCommand;
import com.mindconnect.application.assessmenttype.dto.AssessmentTypeResponse;
import com.mindconnect.application.assessmenttype.exception.AssessmentTypeNotFoundApplicationException;
import com.mindconnect.domain.assessmenttype.model.aggregate.AssessmentType;
import com.mindconnect.domain.assessmenttype.model.valueobject.AssessmentTypeId;
import com.mindconnect.domain.assessmenttype.port.repository.AssessmentTypeRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AssessmentTypeUseCasesTest {

    private FakeAssessmentTypeRepository repository;
    private RegisterAssessmentTypeUseCase register;
    private GetAssessmentTypeByIdUseCase getById;
    private ListAssessmentTypeUseCase list;
    private UpdateAssessmentTypeUseCase update;

    @BeforeEach
    void setUp() {
        repository = new FakeAssessmentTypeRepository();
        register = new RegisterAssessmentTypeUseCase(repository);
        getById = new GetAssessmentTypeByIdUseCase(repository);
        list = new ListAssessmentTypeUseCase(repository);
        update = new UpdateAssessmentTypeUseCase(repository);
    }

    @Test
    void registrarGuardaYDevuelveElRegistroConIdGenerado() {
        AssessmentTypeResponse created = register.execute(commandA());

        assertNotNull(created.id());
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void buscarPorIdDevuelveLoGuardado() {
        AssessmentTypeResponse created = register.execute(commandA());

        assertEquals(created, getById.execute(new AssessmentTypeId(created.id())));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(AssessmentTypeNotFoundApplicationException.class, () -> getById.execute(AssessmentTypeId.generate()));
    }

    @Test
    void listarDevuelveTodosLosRegistros() {
        register.execute(commandA());
        register.execute(commandA());

        assertEquals(2, list.execute().size());
    }

    @Test
    void actualizarConservaElId() {
        AssessmentTypeResponse created = register.execute(commandA());

        AssessmentTypeResponse updated = update.execute(updateCommand(new AssessmentTypeId(created.id())));

        assertEquals(created.id(), updated.id());
        assertEquals(updated, getById.execute(new AssessmentTypeId(created.id())));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(AssessmentTypeNotFoundApplicationException.class, () -> update.execute(updateCommand(AssessmentTypeId.generate())));
    }

    private RegisterAssessmentTypeCommand commandA() {
        return new RegisterAssessmentTypeCommand("valor-a", "valor-a", "valor-a");
    }

    private UpdateAssessmentTypeCommand updateCommand(AssessmentTypeId id) {
        return new UpdateAssessmentTypeCommand(id, "valor-b", "valor-b", "valor-b");
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeAssessmentTypeRepository implements AssessmentTypeRepository {
        private final Map<AssessmentTypeId, AssessmentType> store = new LinkedHashMap<>();

        @Override
        public AssessmentType save(AssessmentType aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<AssessmentType> findById(AssessmentTypeId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<AssessmentType> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(AssessmentType aggregate) {
            store.remove(aggregate.id());
        }
    }
}
