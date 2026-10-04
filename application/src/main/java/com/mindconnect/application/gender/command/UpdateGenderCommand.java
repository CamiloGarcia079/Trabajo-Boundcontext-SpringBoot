package com.mindconnect.application.gender.command;


import com.mindconnect.domain.gender.model.valueobject.GenderId;

public record UpdateGenderCommand(
        GenderId id,
        String description
) {
}
