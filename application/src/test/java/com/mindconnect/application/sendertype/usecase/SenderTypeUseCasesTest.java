package com.mindconnect.application.sendertype.usecase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.sendertype.command.RegisterSenderTypeCommand;
import com.mindconnect.application.sendertype.command.UpdateSenderTypeCommand;
import com.mindconnect.application.sendertype.dto.SenderTypeResponse;
import com.mindconnect.application.sendertype.exception.SenderTypeNotFoundApplicationException;
import com.mindconnect.domain.sendertype.model.aggregate.SenderType;
import com.mindconnect.domain.sendertype.model.valueobject.SenderTypeId;
import com.mindconnect.domain.sendertype.port.repository.SenderTypeRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SenderTypeUseCasesTest {

    private FakeSenderTypeRepository repository;
    private RegisterSenderTypeUseCase register;
    private GetSenderTypeByIdUseCase getById;
    private ListSenderTypeUseCase list;
    private UpdateSenderTypeUseCase update;

    @BeforeEach
    void setUp() {
        repository = new FakeSenderTypeRepository();
        register = new RegisterSenderTypeUseCase(repository);
        getById = new GetSenderTypeByIdUseCase(repository);
        list = new ListSenderTypeUseCase(repository);
        update = new UpdateSenderTypeUseCase(repository);
    }

    @Test
    void registrarGuardaYDevuelveElRegistroConIdGenerado() {
        SenderTypeResponse created = register.execute(commandA());

        assertNotNull(created.id());
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void buscarPorIdDevuelveLoGuardado() {
        SenderTypeResponse created = register.execute(commandA());

        assertEquals(created, getById.execute(new SenderTypeId(created.id())));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(SenderTypeNotFoundApplicationException.class, () -> getById.execute(SenderTypeId.generate()));
    }

    @Test
    void listarDevuelveTodosLosRegistros() {
        register.execute(commandA());
        register.execute(commandA());

        assertEquals(2, list.execute().size());
    }

    @Test
    void actualizarConservaElId() {
        SenderTypeResponse created = register.execute(commandA());

        SenderTypeResponse updated = update.execute(updateCommand(new SenderTypeId(created.id())));

        assertEquals(created.id(), updated.id());
        assertEquals(updated, getById.execute(new SenderTypeId(created.id())));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(SenderTypeNotFoundApplicationException.class, () -> update.execute(updateCommand(SenderTypeId.generate())));
    }

    private RegisterSenderTypeCommand commandA() {
        return new RegisterSenderTypeCommand("valor-a");
    }

    private UpdateSenderTypeCommand updateCommand(SenderTypeId id) {
        return new UpdateSenderTypeCommand(id, "valor-b");
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeSenderTypeRepository implements SenderTypeRepository {
        private final Map<SenderTypeId, SenderType> store = new LinkedHashMap<>();

        @Override
        public SenderType save(SenderType aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<SenderType> findById(SenderTypeId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<SenderType> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(SenderType aggregate) {
            store.remove(aggregate.id());
        }
    }
}
