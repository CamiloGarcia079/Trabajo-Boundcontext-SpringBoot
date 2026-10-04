package com.mindconnect.application.riskassessment.usecase;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.riskassessment.command.RegisterRiskAssessmentCommand;
import com.mindconnect.application.riskassessment.command.UpdateRiskAssessmentCommand;
import com.mindconnect.application.riskassessment.dto.RiskAssessmentResponse;
import com.mindconnect.application.riskassessment.exception.RiskAssessmentNotFoundApplicationException;
import com.mindconnect.domain.riskassessment.model.aggregate.RiskAssessment;
import com.mindconnect.domain.riskassessment.model.valueobject.RiskAssessmentId;
import com.mindconnect.domain.riskassessment.port.repository.RiskAssessmentRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RiskAssessmentUseCasesTest {

    private FakeRiskAssessmentRepository repository;
    private RegisterRiskAssessmentUseCase register;
    private GetRiskAssessmentByIdUseCase getById;
    private ListRiskAssessmentUseCase list;
    private UpdateRiskAssessmentUseCase update;

    @BeforeEach
    void setUp() {
        repository = new FakeRiskAssessmentRepository();
        register = new RegisterRiskAssessmentUseCase(repository);
        getById = new GetRiskAssessmentByIdUseCase(repository);
        list = new ListRiskAssessmentUseCase(repository);
        update = new UpdateRiskAssessmentUseCase(repository);
    }

    @Test
    void registrarGuardaYDevuelveElRegistroConIdGenerado() {
        RiskAssessmentResponse created = register.execute(commandA());

        assertNotNull(created.id());
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void buscarPorIdDevuelveLoGuardado() {
        RiskAssessmentResponse created = register.execute(commandA());

        assertEquals(created, getById.execute(new RiskAssessmentId(created.id())));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(RiskAssessmentNotFoundApplicationException.class, () -> getById.execute(RiskAssessmentId.generate()));
    }

    @Test
    void listarDevuelveTodosLosRegistros() {
        register.execute(commandA());
        register.execute(commandA());

        assertEquals(2, list.execute().size());
    }

    @Test
    void actualizarConservaElId() {
        RiskAssessmentResponse created = register.execute(commandA());

        RiskAssessmentResponse updated = update.execute(updateCommand(new RiskAssessmentId(created.id())));

        assertEquals(created.id(), updated.id());
        assertEquals(updated, getById.execute(new RiskAssessmentId(created.id())));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(RiskAssessmentNotFoundApplicationException.class, () -> update.execute(updateCommand(RiskAssessmentId.generate())));
    }

    private RegisterRiskAssessmentCommand commandA() {
        return new RegisterRiskAssessmentCommand(UUID.randomUUID(), UUID.randomUUID(), true, true, true, true, true, "valor-a", "valor-a", "valor-a", "valor-a", LocalDateTime.now(), UUID.randomUUID());
    }

    private UpdateRiskAssessmentCommand updateCommand(RiskAssessmentId id) {
        return new UpdateRiskAssessmentCommand(id, UUID.randomUUID(), UUID.randomUUID(), false, false, false, false, false, "valor-b", "valor-b", "valor-b", "valor-b", LocalDateTime.now(), UUID.randomUUID());
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeRiskAssessmentRepository implements RiskAssessmentRepository {
        private final Map<RiskAssessmentId, RiskAssessment> store = new LinkedHashMap<>();

        @Override
        public RiskAssessment save(RiskAssessment aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<RiskAssessment> findById(RiskAssessmentId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<RiskAssessment> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(RiskAssessment aggregate) {
            store.remove(aggregate.id());
        }
    }
}
