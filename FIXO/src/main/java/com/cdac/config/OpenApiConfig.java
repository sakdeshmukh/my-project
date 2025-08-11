package com.cdac.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl("http://localhost:8080");
        devServer.setDescription("Server URL in Development environment");

        Contact contact = new Contact();
        contact.setEmail("admin@fixo.com");
        contact.setName("FIXO Support");
        contact.setUrl("https://www.fixo.com");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("FIXO API")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints for FIXO service booking platform. Use the 'Authorize' button to add your JWT token for testing secured endpoints.")
                .termsOfService("https://www.fixo.com/terms")
                .license(mitLicense);

        // Define security scheme for JWT
        SecurityScheme bearerAuthScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .description("JWT token for authentication. Use format: Bearer <your-jwt-token>");

        // Define security requirement
        SecurityRequirement securityRequirement = new SecurityRequirement().addList("bearerAuth");

        return new OpenAPI()
                .info(info)
                .servers(List.of(devServer))
                .addSecurityItem(securityRequirement)
                .components(
                        new Components()
                                .addSecuritySchemes("bearerAuth", bearerAuthScheme)
                );
    }
}
