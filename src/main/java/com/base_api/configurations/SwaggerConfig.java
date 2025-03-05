package com.base_api.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("DUX SOFTWARE | PRUEBA TÉCNICA ")
                        .version("1.0.0")
                        .description("documentación para la prueba técnica de DUX SOFTWARE")
                        .contact(new io.swagger.v3.oas.models.info.Contact().name("Support").email("support@example.com"))
                        .license(new io.swagger.v3.oas.models.info.License().name("Apache 2.0").url("http://springdoc.org")));
    }
}
