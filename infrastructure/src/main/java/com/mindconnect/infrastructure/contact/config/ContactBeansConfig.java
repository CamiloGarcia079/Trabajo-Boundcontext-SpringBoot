package com.mindconnect.infrastructure.contact.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.contact.usecase.DeleteContactUseCase;
import com.mindconnect.application.contact.usecase.GetContactByIdUseCase;
import com.mindconnect.application.contact.usecase.ListContactUseCase;
import com.mindconnect.application.contact.usecase.RegisterContactUseCase;
import com.mindconnect.application.contact.usecase.UpdateContactUseCase;
import com.mindconnect.domain.contact.port.repository.ContactRepository;
import com.mindconnect.infrastructure.contact.adapters.out.persistence.mappers.ContactPersistenceMapper;
import com.mindconnect.infrastructure.contact.adapters.out.persistence.repositories.ContactJpaRepository;
import com.mindconnect.infrastructure.contact.adapters.out.persistence.repositories.ContactRepositoryAdapter;

/**
 * Conecta las piezas del contexto contact: mapper, repositorio y casos de uso.
 * Los casos de uso no usan Spring; aquí se crean y se les entrega lo que necesitan.
 */
@Configuration
public class ContactBeansConfig {

    @Bean
    public ContactPersistenceMapper contactPersistenceMapper() {
        return new ContactPersistenceMapper();
    }

    @Bean
    public ContactRepository contactRepository(ContactJpaRepository repository, ContactPersistenceMapper mapper) {
        return new ContactRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterContactUseCase registerContactUseCase(ContactRepository repository) {
        return new RegisterContactUseCase(repository);
    }

    @Bean
    public GetContactByIdUseCase getContactByIdUseCase(ContactRepository repository) {
        return new GetContactByIdUseCase(repository);
    }

    @Bean
    public ListContactUseCase listContactUseCase(ContactRepository repository) {
        return new ListContactUseCase(repository);
    }

    @Bean
    public UpdateContactUseCase updateContactUseCase(ContactRepository repository) {
        return new UpdateContactUseCase(repository);
    }

    @Bean
    public DeleteContactUseCase deleteContactUseCase(ContactRepository repository) {
        return new DeleteContactUseCase(repository);
    }
}
