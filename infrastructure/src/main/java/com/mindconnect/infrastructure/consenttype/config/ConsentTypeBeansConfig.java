package com.mindconnect.infrastructure.consenttype.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.consenttype.usecase.DeleteConsentTypeUseCase;
import com.mindconnect.application.consenttype.usecase.GetConsentTypeByIdUseCase;
import com.mindconnect.application.consenttype.usecase.ListConsentTypeUseCase;
import com.mindconnect.application.consenttype.usecase.RegisterConsentTypeUseCase;
import com.mindconnect.application.consenttype.usecase.UpdateConsentTypeUseCase;
import com.mindconnect.domain.consenttype.port.repository.ConsentTypeRepository;
import com.mindconnect.infrastructure.consenttype.adapters.out.persistence.mappers.ConsentTypePersistenceMapper;
import com.mindconnect.infrastructure.consenttype.adapters.out.persistence.repositories.ConsentTypeJpaRepository;
import com.mindconnect.infrastructure.consenttype.adapters.out.persistence.repositories.ConsentTypeRepositoryAdapter;

/**
 * Conecta las piezas del contexto consenttype: mapper, repositorio y casos de uso.
 * Los casos de uso no usan Spring; aquí se crean y se les entrega lo que necesitan.
 */
@Configuration
public class ConsentTypeBeansConfig {

    @Bean
    public ConsentTypePersistenceMapper consentTypePersistenceMapper() {
        return new ConsentTypePersistenceMapper();
    }

    @Bean
    public ConsentTypeRepository consentTypeRepository(ConsentTypeJpaRepository repository, ConsentTypePersistenceMapper mapper) {
        return new ConsentTypeRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterConsentTypeUseCase registerConsentTypeUseCase(ConsentTypeRepository repository) {
        return new RegisterConsentTypeUseCase(repository);
    }

    @Bean
    public GetConsentTypeByIdUseCase getConsentTypeByIdUseCase(ConsentTypeRepository repository) {
        return new GetConsentTypeByIdUseCase(repository);
    }

    @Bean
    public ListConsentTypeUseCase listConsentTypeUseCase(ConsentTypeRepository repository) {
        return new ListConsentTypeUseCase(repository);
    }

    @Bean
    public UpdateConsentTypeUseCase updateConsentTypeUseCase(ConsentTypeRepository repository) {
        return new UpdateConsentTypeUseCase(repository);
    }

    @Bean
    public DeleteConsentTypeUseCase deleteConsentTypeUseCase(ConsentTypeRepository repository) {
        return new DeleteConsentTypeUseCase(repository);
    }
}
