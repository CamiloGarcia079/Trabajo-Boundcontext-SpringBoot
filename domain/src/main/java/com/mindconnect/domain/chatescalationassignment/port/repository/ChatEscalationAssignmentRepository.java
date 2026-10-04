package com.mindconnect.domain.chatescalationassignment.port.repository;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.chatescalationassignment.model.aggregate.ChatEscalationAssignment;
import com.mindconnect.domain.chatescalationassignment.model.valueobject.ChatEscalationAssignmentId;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y buscar ChatEscalationAssignment.
 * Lo implementa la infraestructura.
 */
public interface ChatEscalationAssignmentRepository {

    ChatEscalationAssignment save(ChatEscalationAssignment aggregate);

    Optional<ChatEscalationAssignment> findById(ChatEscalationAssignmentId id);

    List<ChatEscalationAssignment> findAll();

    void delete(ChatEscalationAssignment aggregate);
}
