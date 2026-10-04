package com.mindconnect.domain.chatairunmetric.port.repository;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.chatairunmetric.model.aggregate.ChatAiRunMetric;
import com.mindconnect.domain.chatairunmetric.model.valueobject.ChatAiRunMetricId;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y buscar ChatAiRunMetric.
 * Lo implementa la infraestructura.
 */
public interface ChatAiRunMetricRepository {

    ChatAiRunMetric save(ChatAiRunMetric aggregate);

    Optional<ChatAiRunMetric> findById(ChatAiRunMetricId id);

    List<ChatAiRunMetric> findAll();

    void delete(ChatAiRunMetric aggregate);
}
