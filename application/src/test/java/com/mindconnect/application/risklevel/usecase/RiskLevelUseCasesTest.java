package com.mindconnect.application.risklevel.usecase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.risklevel.command.RegisterRiskLevelCommand;
import com.mindconnect.application.risklevel.command.UpdateRiskLevelCommand;
import com.mindconnect.application.risklevel.dto.RiskLevelResponse;
import com.mindconnect.application.risklevel.exception.RiskLevelNotFoundApplicationException;
import com.mindconnect.domain.risklevel.model.aggregate.RiskLevel;
import com.mindconnect.domain.risklevel.model.valueobject.RiskLevelId;
import com.mindconnect.domain.risklevel.port.repository.RiskLevelRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RiskLevelUseCasesTest {

    private FakeRiskLevelRepository repository;
    private RegisterRiskLevelUseCase register;
    private GetRiskLevelByIdUseCase getById;
    private ListRiskLevelUseCase list;
    private UpdateRiskLevelUseCase update;

    @BeforeEach
    void setUp() {
        repository = new FakeRiskLevelRepository();
        register = new RegisterRiskLevelUseCase(repository);
        getById = new GetRiskLevelByIdUseCase(repository);
        list = new ListRiskLevelUseCase(repository);
        update = new UpdateRiskLevelUseCase(repository);
    }

    @Test
    void registrarGuardaYDevuelveElRegistroConIdGenerado() {
        RiskLevelResponse created = register.execute(commandA());

        assertNotNull(created.id());
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void buscarPorIdDevuelveLoGuardado() {
        RiskLevelResponse created = register.execute(commandA());

        assertEquals(created, getById.execute(new RiskLevelId(created.id())));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(RiskLevelNotFoundApplicationException.class, () -> getById.execute(RiskLevelId.generate()));
    }

    @Test
    void listarDevuelveTodosLosRegistros() {
        register.execute(commandA());
        register.execute(commandA());

        assertEquals(2, list.execute().size());
    }

    @Test
    void actualizarConservaElId() {
        RiskLevelResponse created = register.execute(commandA());

        RiskLevelResponse updated = update.execute(updateCommand(new RiskLevelId(created.id())));

        assertEquals(created.id(), updated.id());
        assertEquals(updated, getById.execute(new RiskLevelId(created.id())));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(RiskLevelNotFoundApplicationException.class, () -> update.execute(updateCommand(RiskLevelId.generate())));
    }

    private RegisterRiskLevelCommand commandA() {
        return new RegisterRiskLevelCommand("valor-a", "valor-a", 1);
    }

    private UpdateRiskLevelCommand updateCommand(RiskLevelId id) {
        return new UpdateRiskLevelCommand(id, "valor-b", "valor-b", 2);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeRiskLevelRepository implements RiskLevelRepository {
        private final Map<RiskLevelId, RiskLevel> store = new LinkedHashMap<>();

        @Override
        public RiskLevel save(RiskLevel aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<RiskLevel> findById(RiskLevelId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<RiskLevel> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(RiskLevel aggregate) {
            store.remove(aggregate.id());
        }
    }
}
