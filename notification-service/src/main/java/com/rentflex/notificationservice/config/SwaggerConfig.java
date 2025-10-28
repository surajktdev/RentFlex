package com.rentflex.notificationservice.config;

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
        server.setUrl("http://localhost:8084");
        server.setDescription("Development");

        Contact myContact = new Contact();
        myContact.setName("Suraj Tiwari");
        myContact.setEmail("suraj@gmail.com");

        Info information =
                new Info()
                        .title("RentFlex Notification Service API's")
                        .version("1.0")
                        .description(
                                "APIs for sending and managing notifications in the RentFlex platform. Supports Email, SMS, and Push notifications, tracking delivery status, viewing notification history, and managing user/vendor notification preferences.")
                        .contact(myContact);
        return new OpenAPI()
                .info(information)
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .servers(List.of(server));
    }
}
