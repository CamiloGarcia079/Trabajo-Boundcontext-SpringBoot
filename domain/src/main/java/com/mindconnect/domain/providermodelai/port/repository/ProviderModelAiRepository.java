package com.mindconnect.domain.providermodelai.port.repository;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.providermodelai.model.aggregate.ProviderModelAi;
import com.mindconnect.domain.providermodelai.model.valueobject.ProviderModelAiId;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y buscar ProviderModelAi.
 * Lo implementa la infraestructura.
 */
public interface ProviderModelAiRepository {

    ProviderModelAi save(ProviderModelAi aggregate);

    Optional<ProviderModelAi> findById(ProviderModelAiId id);

    List<ProviderModelAi> findAll();

    void delete(ProviderModelAi aggregate);
}
