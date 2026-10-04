package com.mindconnect.application.providermodelai.command;


public record RegisterProviderModelAiCommand(
        String nameProviderAi,
        String razonSocial,
        String sitioWeb
) {
}
