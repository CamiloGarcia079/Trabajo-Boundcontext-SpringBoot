package com.mindconnect.application.treatmentplan.usecase;

import java.time.LocalDate;
import java.util.UUID;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.treatmentplan.command.RegisterTreatmentPlanCommand;
import com.mindconnect.application.treatmentplan.command.UpdateTreatmentPlanCommand;
import com.mindconnect.application.treatmentplan.dto.TreatmentPlanResponse;
import com.mindconnect.application.treatmentplan.exception.TreatmentPlanNotFoundApplicationException;
import com.mindconnect.domain.treatmentplan.model.aggregate.TreatmentPlan;
import com.mindconnect.domain.treatmentplan.model.valueobject.TreatmentPlanId;
import com.mindconnect.domain.treatmentplan.port.repository.TreatmentPlanRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TreatmentPlanUseCasesTest {

    private FakeTreatmentPlanRepository repository;
    private RegisterTreatmentPlanUseCase register;
    private GetTreatmentPlanByIdUseCase getById;
    private ListTreatmentPlanUseCase list;
    private UpdateTreatmentPlanUseCase update;

    @BeforeEach
    void setUp() {
        repository = new FakeTreatmentPlanRepository();
        register = new RegisterTreatmentPlanUseCase(repository);
        getById = new GetTreatmentPlanByIdUseCase(repository);
        list = new ListTreatmentPlanUseCase(repository);
        update = new UpdateTreatmentPlanUseCase(repository);
    }

    @Test
    void registrarGuardaYDevuelveElRegistroConIdGenerado() {
        TreatmentPlanResponse created = register.execute(commandA());

        assertNotNull(created.id());
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void buscarPorIdDevuelveLoGuardado() {
        TreatmentPlanResponse created = register.execute(commandA());

        assertEquals(created, getById.execute(new TreatmentPlanId(created.id())));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(TreatmentPlanNotFoundApplicationException.class, () -> getById.execute(TreatmentPlanId.generate()));
    }

    @Test
    void listarDevuelveTodosLosRegistros() {
        register.execute(commandA());
        register.execute(commandA());

        assertEquals(2, list.execute().size());
    }

    @Test
    void actualizarConservaElId() {
        TreatmentPlanResponse created = register.execute(commandA());

        TreatmentPlanResponse updated = update.execute(updateCommand(new TreatmentPlanId(created.id())));

        assertEquals(created.id(), updated.id());
        assertEquals(updated, getById.execute(new TreatmentPlanId(created.id())));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(TreatmentPlanNotFoundApplicationException.class, () -> update.execute(updateCommand(TreatmentPlanId.generate())));
    }

    private RegisterTreatmentPlanCommand commandA() {
        return new RegisterTreatmentPlanCommand(UUID.randomUUID(), UUID.randomUUID(), "valor-a", "valor-a", LocalDate.now(), LocalDate.now(), UUID.randomUUID());
    }

    private UpdateTreatmentPlanCommand updateCommand(TreatmentPlanId id) {
        return new UpdateTreatmentPlanCommand(id, UUID.randomUUID(), UUID.randomUUID(), "valor-b", "valor-b", LocalDate.now(), LocalDate.now(), UUID.randomUUID());
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeTreatmentPlanRepository implements TreatmentPlanRepository {
        private final Map<TreatmentPlanId, TreatmentPlan> store = new LinkedHashMap<>();

        @Override
        public TreatmentPlan save(TreatmentPlan aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<TreatmentPlan> findById(TreatmentPlanId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<TreatmentPlan> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(TreatmentPlan aggregate) {
            store.remove(aggregate.id());
        }
    }
}
