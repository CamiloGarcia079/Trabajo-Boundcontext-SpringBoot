package com.mindconnect.application.aimodel.usecase;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.aimodel.command.RegisterAiModelCommand;
import com.mindconnect.application.aimodel.command.UpdateAiModelCommand;
import com.mindconnect.application.aimodel.dto.AiModelResponse;
import com.mindconnect.application.aimodel.exception.AiModelNotFoundApplicationException;
import com.mindconnect.domain.aimodel.model.aggregate.AiModel;
import com.mindconnect.domain.aimodel.model.valueobject.AiModelId;
import com.mindconnect.domain.aimodel.port.repository.AiModelRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AiModelUseCasesTest {

    private FakeAiModelRepository repository;
    private RegisterAiModelUseCase register;
    private GetAiModelByIdUseCase getById;
    private ListAiModelUseCase list;
    private UpdateAiModelUseCase update;

    @BeforeEach
    void setUp() {
        repository = new FakeAiModelRepository();
        register = new RegisterAiModelUseCase(repository);
        getById = new GetAiModelByIdUseCase(repository);
        list = new ListAiModelUseCase(repository);
        update = new UpdateAiModelUseCase(repository);
    }

    @Test
    void registrarGuardaYDevuelveElRegistroConIdGenerado() {
        AiModelResponse created = register.execute(commandA());

        assertNotNull(created.id());
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void buscarPorIdDevuelveLoGuardado() {
        AiModelResponse created = register.execute(commandA());

        assertEquals(created, getById.execute(new AiModelId(created.id())));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(AiModelNotFoundApplicationException.class, () -> getById.execute(AiModelId.generate()));
    }

    @Test
    void listarDevuelveTodosLosRegistros() {
        register.execute(commandA());
        register.execute(commandA());

        assertEquals(2, list.execute().size());
    }

    @Test
    void actualizarConservaElId() {
        AiModelResponse created = register.execute(commandA());

        AiModelResponse updated = update.execute(updateCommand(new AiModelId(created.id())));

        assertEquals(created.id(), updated.id());
        assertEquals(updated, getById.execute(new AiModelId(created.id())));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(AiModelNotFoundApplicationException.class, () -> update.execute(updateCommand(AiModelId.generate())));
    }

    private RegisterAiModelCommand commandA() {
        return new RegisterAiModelCommand(UUID.randomUUID(), "valor-a", "valor-a", BigDecimal.ONE, BigDecimal.ONE, 1, 1);
    }

    private UpdateAiModelCommand updateCommand(AiModelId id) {
        return new UpdateAiModelCommand(id, UUID.randomUUID(), "valor-b", "valor-b", BigDecimal.TEN, BigDecimal.TEN, 2, 2);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeAiModelRepository implements AiModelRepository {
        private final Map<AiModelId, AiModel> store = new LinkedHashMap<>();

        @Override
        public AiModel save(AiModel aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<AiModel> findById(AiModelId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<AiModel> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(AiModel aggregate) {
            store.remove(aggregate.id());
        }
    }
}
