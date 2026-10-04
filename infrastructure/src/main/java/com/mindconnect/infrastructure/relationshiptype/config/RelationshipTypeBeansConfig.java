package com.mindconnect.infrastructure.relationshiptype.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.relationshiptype.usecase.DeleteRelationshipTypeUseCase;
import com.mindconnect.application.relationshiptype.usecase.GetRelationshipTypeByIdUseCase;
import com.mindconnect.application.relationshiptype.usecase.ListRelationshipTypeUseCase;
import com.mindconnect.application.relationshiptype.usecase.RegisterRelationshipTypeUseCase;
import com.mindconnect.application.relationshiptype.usecase.UpdateRelationshipTypeUseCase;
import com.mindconnect.domain.relationshiptype.port.repository.RelationshipTypeRepository;
import com.mindconnect.infrastructure.relationshiptype.adapters.out.persistence.mappers.RelationshipTypePersistenceMapper;
import com.mindconnect.infrastructure.relationshiptype.adapters.out.persistence.repositories.RelationshipTypeJpaRepository;
import com.mindconnect.infrastructure.relationshiptype.adapters.out.persistence.repositories.RelationshipTypeRepositoryAdapter;

/**
 * Conecta las piezas del contexto relationshiptype: mapper, repositorio y casos de uso.
 * Los casos de uso no usan Spring; aquí se crean y se les entrega lo que necesitan.
 */
@Configuration
public class RelationshipTypeBeansConfig {

    @Bean
    public RelationshipTypePersistenceMapper relationshipTypePersistenceMapper() {
        return new RelationshipTypePersistenceMapper();
    }

    @Bean
    public RelationshipTypeRepository relationshipTypeRepository(RelationshipTypeJpaRepository repository, RelationshipTypePersistenceMapper mapper) {
        return new RelationshipTypeRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterRelationshipTypeUseCase registerRelationshipTypeUseCase(RelationshipTypeRepository repository) {
        return new RegisterRelationshipTypeUseCase(repository);
    }

    @Bean
    public GetRelationshipTypeByIdUseCase getRelationshipTypeByIdUseCase(RelationshipTypeRepository repository) {
        return new GetRelationshipTypeByIdUseCase(repository);
    }

    @Bean
    public ListRelationshipTypeUseCase listRelationshipTypeUseCase(RelationshipTypeRepository repository) {
        return new ListRelationshipTypeUseCase(repository);
    }

    @Bean
    public UpdateRelationshipTypeUseCase updateRelationshipTypeUseCase(RelationshipTypeRepository repository) {
        return new UpdateRelationshipTypeUseCase(repository);
    }

    @Bean
    public DeleteRelationshipTypeUseCase deleteRelationshipTypeUseCase(RelationshipTypeRepository repository) {
        return new DeleteRelationshipTypeUseCase(repository);
    }
}
