package com.mindconnect.domain.messagetype.port.repository;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.messagetype.model.aggregate.MessageType;
import com.mindconnect.domain.messagetype.model.valueobject.MessageTypeId;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y buscar MessageType.
 * Lo implementa la infraestructura.
 */
public interface MessageTypeRepository {

    MessageType save(MessageType aggregate);

    Optional<MessageType> findById(MessageTypeId id);

    List<MessageType> findAll();

    void delete(MessageType aggregate);
}
