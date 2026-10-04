package com.mindconnect.application.messagetype.usecase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.messagetype.command.RegisterMessageTypeCommand;
import com.mindconnect.application.messagetype.command.UpdateMessageTypeCommand;
import com.mindconnect.application.messagetype.dto.MessageTypeResponse;
import com.mindconnect.application.messagetype.exception.MessageTypeNotFoundApplicationException;
import com.mindconnect.domain.messagetype.model.aggregate.MessageType;
import com.mindconnect.domain.messagetype.model.valueobject.MessageTypeId;
import com.mindconnect.domain.messagetype.port.repository.MessageTypeRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MessageTypeUseCasesTest {

    private FakeMessageTypeRepository repository;
    private RegisterMessageTypeUseCase register;
    private GetMessageTypeByIdUseCase getById;
    private ListMessageTypeUseCase list;
    private UpdateMessageTypeUseCase update;

    @BeforeEach
    void setUp() {
        repository = new FakeMessageTypeRepository();
        register = new RegisterMessageTypeUseCase(repository);
        getById = new GetMessageTypeByIdUseCase(repository);
        list = new ListMessageTypeUseCase(repository);
        update = new UpdateMessageTypeUseCase(repository);
    }

    @Test
    void registrarGuardaYDevuelveElRegistroConIdGenerado() {
        MessageTypeResponse created = register.execute(commandA());

        assertNotNull(created.id());
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void buscarPorIdDevuelveLoGuardado() {
        MessageTypeResponse created = register.execute(commandA());

        assertEquals(created, getById.execute(new MessageTypeId(created.id())));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(MessageTypeNotFoundApplicationException.class, () -> getById.execute(MessageTypeId.generate()));
    }

    @Test
    void listarDevuelveTodosLosRegistros() {
        register.execute(commandA());
        register.execute(commandA());

        assertEquals(2, list.execute().size());
    }

    @Test
    void actualizarConservaElId() {
        MessageTypeResponse created = register.execute(commandA());

        MessageTypeResponse updated = update.execute(updateCommand(new MessageTypeId(created.id())));

        assertEquals(created.id(), updated.id());
        assertEquals(updated, getById.execute(new MessageTypeId(created.id())));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(MessageTypeNotFoundApplicationException.class, () -> update.execute(updateCommand(MessageTypeId.generate())));
    }

    private RegisterMessageTypeCommand commandA() {
        return new RegisterMessageTypeCommand("valor-a");
    }

    private UpdateMessageTypeCommand updateCommand(MessageTypeId id) {
        return new UpdateMessageTypeCommand(id, "valor-b");
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeMessageTypeRepository implements MessageTypeRepository {
        private final Map<MessageTypeId, MessageType> store = new LinkedHashMap<>();

        @Override
        public MessageType save(MessageType aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<MessageType> findById(MessageTypeId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<MessageType> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(MessageType aggregate) {
            store.remove(aggregate.id());
        }
    }
}
