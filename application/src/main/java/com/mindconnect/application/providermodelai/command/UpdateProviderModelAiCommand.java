package com.mindconnect.application.providermodelai.command;


import com.mindconnect.domain.providermodelai.model.valueobject.ProviderModelAiId;

public record UpdateProviderModelAiCommand(
        ProviderModelAiId id,
        String nameProviderAi,
        String razonSocial,
        String sitioWeb
) {
}
