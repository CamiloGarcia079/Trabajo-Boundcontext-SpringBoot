package com.mindconnect.application.providermodelai.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.providermodelai.model.aggregate.ProviderModelAi;

public record ProviderModelAiResponse(
        UUID id,
        String nameProviderAi,
        String razonSocial,
        String sitioWeb,
        boolean isActive,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public static ProviderModelAiResponse fromDomain(ProviderModelAi aggregate) {
        return new ProviderModelAiResponse(
                aggregate.id().value(),
                aggregate.nameProviderAi(), aggregate.razonSocial(), aggregate.sitioWeb(), aggregate.isActive(), aggregate.createdAt(), aggregate.updatedAt());
    }
}
