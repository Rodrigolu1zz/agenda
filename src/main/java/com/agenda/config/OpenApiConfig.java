package com.agenda.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Para executar deve usar o endpoint: /swagger-ui/index.html

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Agenda de Contatos")
                        .description("API construída para aprendizado de Spring Boot 3 com CRUD completo.")
                        .contact(new Contact().name("Rodrigo Luiz Santos").email("rodrigoluiz924@gmail.com"))
                        .version("1.0.0"));
    }
}