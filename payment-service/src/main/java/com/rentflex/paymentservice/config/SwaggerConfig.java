package com.rentflex.paymentservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI defineOpenApi() {
        final String securitySchemeName = "BearerToken";
        Server server = new Server();
        server.setUrl("http://localhost:8085");
        server.setDescription("Development");

        Contact myContact = new Contact();
        myContact.setName("Suraj Tiwari");
        myContact.setEmail("suraj@gmail.com");

        Info information =
                new Info()
                        .title("RentFlex Payment Service API")
                        .version("1.0")
                        .description(
                                "APIs for processing and managing payments in the RentFlex platform. Handles rental payments, refunds, payment gateway integration, transaction history, and ensures secure financial operations for users and vendors.")
                        .contact(myContact);
        return new OpenAPI()
                .info(information)
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .servers(List.of(server));
    }
}
