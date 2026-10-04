package com.mindconnect.infrastructure;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Levanta el contexto completo de Spring Boot. Al arrancar, Flyway valida las migraciones
 * existentes y aplica las pendientes sobre la base indicada en el .env.
 * Necesita PostgreSQL encendido y las variables DB_* configuradas.
 */
@SpringBootTest
class MindConnectApplicationTests {

    @Test
    void contextLoads() {
    }
}
