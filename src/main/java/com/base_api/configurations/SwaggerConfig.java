package com.base_api.configurations;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("DUX SOFTWARE | PRUEBA TÉCNICA ")
                        .version("1.0.0")
                        .description("Documentación para la prueba técnica de DUX SOFTWARE")
                        .contact(new io.swagger.v3.oas.models.info.Contact().name("Support").email(
                                "support@example.com"))
                        .license(new io.swagger.v3.oas.models.info.License().name("Apache 2.0").url(
                                "http://springdoc.org")))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT"))
                )
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
    }
}
